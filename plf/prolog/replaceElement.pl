
concat([],L,L).
concat([H|T],L,[H|Res]) :- concat(T,L,Res).



replace([],_,_,[]).
replace([E|T],E,L2,R):-
			concat(L2,Aux,R),
			replace(T,E,L2,Aux).
replace([H|T],E,L2,[H|R]):-
			replace(T,E,L2,R).
