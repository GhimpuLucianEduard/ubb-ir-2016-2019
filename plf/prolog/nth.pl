%returneaza al n-lea element dintr-o lista
nth([H|_],1,E):-
	E=H.
nth([_|T],N,E):-
	N1 is N-1,nth(T,N1,E).
