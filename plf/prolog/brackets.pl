%par(int d, int i, list rez)
%(i,i,0)
%returns a/all permutations of brackets, given a N
%d keeps track of open brackets, i of closed ones

par(0,0,[]).
par(D,I,['('|R]):-D>0, D1 is D-1, I1 is I+1, par(D1,I1,R).
par(D,I,[')'|R]):-I>0, I1 is I-1, par(D,I1,R).

%permutations(int n, list r)
%(i,o)
%prints all valid permutations of brackets given a n
permutationsPar(N, R) :- N2 is N div 2, findall(X, par(N2,0, X), R).


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%				 
%				 { vid, daca d==0 and i==0	
%	par(d,i,r) = { par(d-1, i+1, r+"("), daca d>0 
%				 { par(d, i-1, r+")"), daca I>0
%
%
%	permutationsPar(n, r) = findall(x, par(n/2, 0, x), r)SSS
%
%
%
%