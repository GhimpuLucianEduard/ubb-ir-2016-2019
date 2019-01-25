import json

from tabulate import tabulate
import logging

from Lab2 import FiniteAutonoma


class FileIterator:
    """
    class use to iterate the input file
    """
    def __init__(self, file_name):
        self.__file_name = file_name

    # iterator used to retrive next char, row and col
    def file_iterator(self):
        with open(self.__file_name) as input_file:
            # usual double for loop for each line, for each char
            for i, line in enumerate(input_file):
                for j, char in enumerate(line):
                    if char:
                        # return the value as a tuple
                        yield (char, i, j)
                    else:
                        # else just return, we parsed to the end
                        return


class Parser:
    def __init__(self, symbols_file, input_file):
        """
        :param symbols_file: file used to load keywords
        :param input_file: input file to be parsed
        """
        # file used to load keywords
        self.__symbols_table_filename = symbols_file
        # dict used to store keywords
        self.__symbols_dict = {}
        # load a file iterator
        self.__file_iterator = FileIterator(input_file)
        # list of all parsed atoms
        self.__atoms = []
        # list of all valid atoms to be writen in the output file
        self.__output_atoms = []
        # hash table of all identifiers and constants
        self.__output_ts = dict()
        # id used to insert into the hash table
        self.__last_ts_id = 0

        file_ident = open("C:\Users\Fabby\Documents\LFTC\Laboratore\Lab2\input_fa\ident.json")
        file_const = open("C:\Users\Fabby\Documents\LFTC\Laboratore\Lab2\input_fa\const.json")
        self.__ident_fa = FiniteAutonoma.FiniteAutomata(json.load(file_ident))
        self.__const_fa = FiniteAutonoma.FiniteAutomata(json.load(file_const))


    def load_symbols_table(self):
        """
        Creates a dictionary of {int: symbol_code, string: symbol},
        loaded from given file.
        """
        table_file = open(self.__symbols_table_filename)
        for line in table_file:
            (symbol, code) = line.split()
            self.__symbols_dict[int(code)] = symbol

    def is_char_valid_symbol(self, char):
        """
        :param char: char to be validated
        :return: true if char is a valid separator, false otherwise
        """
        return char != " " and char != "" and char != "\n" and not char.isspace();

    def check_if_var(self, var):
        """
        :param var: atom to be validated as an identifier
        :return: true if atom is valid, false otherwise
        """
        # for char in var:
        #     if not char.isalpha():
        #         return False
        # return True
        return self.__ident_fa.check_atom(var)

    def check_if_const(self, const):
        """
        :param const: atom to be validated as a constant
        :return: true if atom is valid, false otherwise
        """
        # for char in const:
        #     if not char.isdigit() and char != ".":
        #         return False
        # return True
        return self.__const_fa.check_atom(const)

    def check_if_keyword(self, string):
        """
        :param string: atom to be validated as a keyword
        :return: true if atom is valid, false otherwise
        """
        for keyword in self.__symbols_dict.values():
            if keyword == string:
                return True
        return False

    def get_atom_type(self, atom):
        """
        Returns the type of an atom
        :param atom: given atom to check the type
        :return: int (0, 1, 2, 3, 4) based on atom type
        """
        if self.check_if_keyword(atom):
            # 1 for keyword
            return 1
        if self.check_if_var(atom):
            # 2 for identifier
            return 2
        if self.check_if_const(atom):
            # 3 for constant
            return 3
        if atom == " " or atom == "" or atom == '' or atom == "\n" or atom.isspace():
            # 4 for generic separators
            return 4
        return 0

    def get_atom_code(self, atom):
        """
        Returns atom code from loaded symbols table
        :param atom: given atom
        :return: int representing the atom code
        """
        for code, symbol in self.__symbols_dict.items():
            # if keyword, return associated code
            if symbol == atom:
                return code

        if self.check_if_var(atom):
            # if identifier, return 0
            return 0
        if self.check_if_const(atom):
            # if constant, return 1
            return 1

        # invalid atom
        return -1

    def append_atom_to_output(self, atom):
        """
        Appends an atom to be appended to output containers (TS hash talbe and output atoms list)
        :param atom: given valid atom to be appended to output containers
        """
        atom_type = self.get_atom_type(atom)
        atom_code = self.get_atom_code(atom)
        if atom_type == 1:
            # keyword just write it, nothing special to handle
            self.__output_atoms.append((atom, atom_code, "---"))
        elif atom_type == 2 or atom_type == 3:
            # identifiers and constants are stored in both TS and normal output file
            self.__output_ts[self.__last_ts_id + 1] = atom
            self.__output_atoms.append((atom, atom_code, self.__last_ts_id + 1))
            self.__last_ts_id += 1

    def print_error(self, i, j, message="Invalid token"):
        """
        :param i: row index of file input
        :param j: col index of file input
        :param message: additional message
        """
        logging.error("ERROR")
        logging.error(message)
        logging.error("Invalid token at line: %s column: %s" % (i, j))

    def extract_atoms(self):
        """
        Main method used to parse and extract the input file
        """
        # get the iterator instance
        iterator = self.__file_iterator.file_iterator()
        try:
            # do until we reach the iterator end (exception is thrown)
            while True:
                # default empty values for
                # atom is the atom which we build we parsing the document
                # char is the next char from the file iterator
                atom = ""
                last_symbol = ""
                (char, i, j) = iterator.next()
                if char.isalpha():
                    # handle letters
                    while char.isalpha():
                        atom += char
                        (char, i, j) = iterator.next()
                    # no more letters, keep the last char
                    if not char.isalpha() and self.is_char_valid_symbol(char):
                        last_symbol = char
                elif char.isdigit():
                    # handle digits and "." for float numbers
                    while char.isdigit() or char == ".":
                        atom += char
                        (char, i, j) = iterator.next()
                    # no more digits, keep the last char
                    if not char.isdigit() and self.is_char_valid_symbol(char):
                        last_symbol = char
                elif char == '>' or char == '<' or char == '=' or char == '!':
                    # special case where after "<, >, =, !" we could have an "="
                    (next_char, i, j) = iterator.next()
                    if next_char == "=":
                        # if the next char is an "=", combine it with the last char and save it as a whole
                        char += next_char
                        self.__atoms.append(char)
                        self.append_atom_to_output(char)
                        # if not, just store them separately
                    elif next_char != ' ':
                        # no spaces allowed in my language :) just to make my work easier, please no hate
                        # we have ides nowadays that can handle file formatting for spaces
                        self.print_error(i, j)
                        break
                    else:
                        # no = or no space, check if the char is valid and store it
                        if self.is_char_valid_symbol(char):
                            self.__atoms.append(char)
                            self.append_atom_to_output(char)
                        else:
                            # invalid char, print error and break execution
                            self.print_error(i, j)
                            break
                else:
                    # no letter, no digits, no special cases, must be a symbol (i.e. { } ; etc...)
                    atom = char

                # now that we have an atom, we should check if is valid and store it

                if self.is_char_valid_symbol(atom):
                    # should also check if the length is grater than 250
                    if atom.__len__() >= 250:
                        # if so, print error and break execution
                        self.print_error(i, j - atom.__len__(),
                                         "Token size limit, please use tokens shorter than 250 characters")
                        break
                    if self.get_atom_type(atom) == 0:
                        # an additional validation, break if the type of the atom is not valid
                        self.print_error(i, j)
                        break

                    # validation completed, store the atom
                    self.__atoms.append(atom)
                    self.append_atom_to_output(atom)
                if self.is_char_valid_symbol(last_symbol):
                    # handle any leftover char (happens cause of the while loops)
                    # if char is valid, store it, otherwise print error
                    if self.get_atom_type(last_symbol) == 0:
                        self.print_error(i, j)
                        break
                    else:
                        self.__atoms.append(last_symbol)
                        self.append_atom_to_output(last_symbol)
        except StopIteration:
            # We finished parsing the file
            print("Finished parsing the input file...")

    def write_output(self):
        # open the output files and print the atoms
        output_atoms = open("output_atoms", 'w')
        output_ts = open("output_ts", 'w')
        output_atoms.write(tabulate(self.__output_atoms, headers=['Atom', "Cod_Atom", 'Cod_TS']))
        output_ts.write(tabulate(self.__output_ts.items(), headers=['Atom', 'Cod_TS']))

    def parse_and_print(self):
        # do all operations
        self.load_symbols_table()
        self.extract_atoms()
        self.write_output()


input_file = raw_input("Tipareste nume fisier:")
parser = Parser("C:\Users\Fabby\Documents\LFTC\Laboratore\Simboluri",
                "C:\Users\Fabby\Documents\LFTC\Laboratore\input_files\\" + input_file)
parser.parse_and_print()
