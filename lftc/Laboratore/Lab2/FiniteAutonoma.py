class FiniteAutomata:
    def __init__(self, json):
        self.__states = ()
        # dictionary of dictionaries, used to store all the states and transitions
        self.__matrix = {}
        # json file used to store the finite automata
        self.__json = json
        # read the automata form the json
        self.read_json()

    def read_json(self):
        # for each transitions, build the "transitions" matrix
        for transition in self.__json["transitions"]:
            if transition["from"] not in self.__matrix:
                self.__matrix[transition["from"]] = {}
            # check if symbol is a list or an single item
            if type(transition["symbol"]) is list:
                # if list, loop
                for symbol in transition["symbol"]:
                    self.__matrix[transition["from"]][symbol] = transition["to"]
            else:
                # else, just add the item
                self.__matrix[transition["from"]][transition["symbol"]] = transition["to"]

    def encode_ascii(self, list):
        # utils method
        return map(lambda x: x.encode('ascii'), list)

    def print_states(self):
        print "States:"
        print self.encode_ascii(self.__json["states"])

    def print_alphabet(self):
        print "Alphabet:"
        print self.encode_ascii(self.__json["alphabet"])

    def print_transitions(self):
        print "Transitions:"
        for transition in self.__json["transitions"]:
            print ("From: %s    to: %s   symbol: %s" % (
                transition["from"], transition["to"], self.encode_ascii(transition["symbol"])))

    def print_final_states(self):
        print "Final states:"
        print self.encode_ascii(self.__json["finalStates"])

    # check if an atom is valid, return true or false
    def check_atom(self, value):
        # get initial state
        current_state = self.__json["initialState"]
        for character in value:
            # for each char, go from state to state and check if it's a valid transition
            if current_state in self.__matrix and character in self.__matrix[current_state]:
                current_state = self.__matrix[current_state][character]
            else:
                # if something is not valid, return false
                return False
        
        #finally, check if the current state is a final state
        return current_state in self.__json["finalStates"]

    def check_longest_prefix(self, value):
        # get initial state
        current_state = self.__json["initialState"]
        current_atom = ""
        # loop for each char in the given value
        for character in value:
            # similar as check_atom, we go from state to state while the char is valid
            if current_state in self.__matrix and character in self.__matrix[current_state]:
                current_atom += character
                current_state = self.__matrix[current_state][character]
            else:
                # return the atom when something is not valid
                return current_atom
        # return the atom when we reach the final state
        return current_atom