import json

from Lab2.FiniteAutonoma import FiniteAutomata

# class used to dispaly an UI
class Menu:

    # needs reference to an automata
    def __init__(self, autonoma):
        self.__autonoma = autonoma

    def print_menu(self):
        print("=========================================================")
        print("1. Print set of states")
        print("2. Print alphabet")
        print("3. Print all transitions")
        print("4. Print final states")
        print("5. Check if atom is valid")
        print("6. Get the longest prefix ")
        print("7. Run for test file")
        print("0. exit")
        print("=========================================================")

    def run(self):
        while True:
            self.print_menu()
            command = input("Please type a command...")
            if command == 1:
                self.__autonoma.print_states()
            elif command == 2:
                self.__autonoma.print_alphabet()
            elif command == 3:
                self.__autonoma.print_transitions()
            elif command == 4:
                self.__autonoma.print_final_states()
            elif command == 5:
                value = raw_input("type the atom here...")
                print ("Atom is valid: %s" % (self.__autonoma.check_atom(value)))
            elif command == 6:
                value = raw_input("type the value here...")
                print("Longest accepted prefix: '%s'" % (self.__autonoma.check_longest_prefix(value)))
            elif command == 7:
                self.run_test_file()
            elif command == 0:
                print("Exit")
                return
            else:
                print("Invalid command")

    def run_test_file(self):
        test_file = open("C:\Users\Fabby\Documents\LFTC\Laboratore\Lab2\\test.txt")
        for line in test_file:
            isValid = self.__autonoma.check_atom(line.rstrip())
            if isValid:
                print ("Atom: %s is valid" % line)
            else:
                print ("Atom: %s is not valid" % line)
                print ("Longest accepted prefix: '%s'" % (self.__autonoma.check_longest_prefix(line)))


file = open("input_fa/cpp.json")
automata = FiniteAutomata(json.load(file))
menu = Menu(automata)
menu.run()
