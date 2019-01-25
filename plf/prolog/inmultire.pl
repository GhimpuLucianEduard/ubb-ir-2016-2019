zece(E,[]):-E=0.
zece(E,[_|T]):-E=1+zece(E,[T]).

inmultire(E,[],R):-R=0.
inmultire(E,[H|T],R):-R=inmultire(E,H,R)+T*E.
