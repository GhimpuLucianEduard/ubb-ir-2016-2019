%addToEnd(int E, list L, list Rez)
%(i,i,o)
%add E to the end of the list L
addToEnd(E,[],[E]).
addToEnd(E,[H|T],[H|L]):-addToEnd(E,T,L).

%reverse(list l, list rez)
%(i,o)
%reverse the list l
reverse([],[]).
reverse([H|T],L):-reverse(T,L1),addToEnd(H,L1,L).


%sum(list l1, list l2, int t, list rez)
%(i,i,i,o)
%add 2 numbers represented as lists, t used as carry flag
%output rez, the sum of l1 and l2, as list
sum([],[],CT,[]):-CT is 0.
sum([],[],CT,[CT|_]):-CT>0.
sum([H|T],[],CT,[H2|R]):-
	H2 is (H+CT) mod 10,
	CT1 is (H+CT) div 10,
        sum(T,[],CT1,R).

sum([],[H|T],CT,[H2|R]):-
	H2 is (H+CT) mod 10,
	CT1 is (H+CT) div 10,
        sum([],T,CT1,R).


sum([H|T],[H1|T1],CT,[H2|R]):-
	H2 is ((H+H1)+CT) mod 10,
	CT1 is ((H + H1)+CT) div 10,
        sum(T,T1,CT1,R).

%sumWrap(list l1, list l2, list rez)
%(i,i,o)
%wrapper function for sum
sumWrap(L1,L2,R):-reverse(L1,L11),reverse(L2,L22),
	sum(L11,L22,0,R1), reverse(R1,R).


