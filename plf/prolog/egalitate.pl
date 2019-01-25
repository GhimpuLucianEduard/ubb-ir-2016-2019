%Verifica daca un element E se afla intr-o lista
member(E,[E|_]).
member(E,[_|T]):-member(E,T).

%verifica daca o multime M1 e submultime a lui M2
submultime([],_).
submultime([H1|T1],L2):-
	member(H1,L2),
	submultime(T1,L2).

%Verifica daca 2 multimi sunt egale
egal(L1,L2):-submultime(L1,L2),submultime(L2,L1).
