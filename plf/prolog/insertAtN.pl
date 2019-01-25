


insertAtN([],_,_,_,[]).
insertAtN([H|T],I,N,E,[E,H|R]):-
		I=N,
		N2 is N+2,
		I2 is I+1,
		insertAtN(T,I2,N2,E,R).
insertAtN([H|T],I,N,E,[H|R]):-
		not(I=N),
		I2 is I+1,
		insertAtN(T,I2,N,E,R).





