adaugaFinal(E,[],[E]).
adaugaFinal(E,[H|T],[H|L]):-adaugaFinal(E,T,L).

invers([],[]).
invers([H|T],L):-invers(T,L1),adaugaFinal(H,L1,L).




inmultireRec([],_,CT,[]):-CT is 0.
inmultireRec([],_,CT,[CT]):-CT>0.
inmultireRec([H|T],E,CT,[H1|RL]):-
	H1 is (mod((H*E)+CT, 10)),
	CT1 is ((H*E)+CT) div 10,
	inmultireRec(T,E,CT1,RL).


cerintaA(L,R,E):-invers(L,L1),inmultireRec(L1,E,0,R1), invers(R1,R).




cerintaB([],_,[]).
cerintaB([H|T],I,R):-
	integer(H),
	cerintaB(T,I,R1),
	R=[H|R1].
cerintaB([H|T],I,R):-
	\+ integer(H),
	I1 is I+1,
	cerintaB(T,I1,R1),
	R=[H1|R1],
	cerintaA(H,H1,I1).

finalB(L,R):-cerintaB(L,0,R).













