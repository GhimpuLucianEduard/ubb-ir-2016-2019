%max(list l, int aux, int max)
%(i,i,o)
%output the max element of the list l
%aux used to keep track of the max, must be init. with 0
max([],A,A).
max([H|T],A,R):-H>A, max(T,H,R).
max([H|T],A,R):-H=<A, max(T,A,R).

%indexMax(list l, int max, int index, list rez)
%(i,i,i,o)
%output the list with all the indexes where max is in the list l
%index used to keep track of the current index, must be init. with 0
%M is the max of the list
indexMax([],M,_,[]).
indexMax([H|T],M,I,[I2|NL]):-
		M=H,
		I2 is I+1,
		indexMax(T,M,I2,NL).
indexMax([H|T],M,I,NL):-
		I2 is I+1,
		indexMax(T,M,I2,NL).

%(list l, list rez)
%(i,o)
%wrapper function for indexMax and max
indexMaxWrap(L,R):-max(L,0,M), indexMax(L,M,0,R).