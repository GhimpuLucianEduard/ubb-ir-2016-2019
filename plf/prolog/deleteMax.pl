
max([],A,A).
max([H|T],A,R):-H>A, max(T,H,R).
max([H|T],A,R):-H=<A, max(T,A,R).


delete([],_,[]).
delete([H|T],E,R):-E=H, delete(T,E,R).
delete([H|T],E,[H|R]):-not(H=E), delete(T,E,R).