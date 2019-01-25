%Insertion sort

%insert(int nr, list l, list rez)
%(i,i,o)
%returns a new list with nr instered in the right place
insert(X,[],[X]).
insert(X,[H|T],[X,H|T]):-X=H.
insert(X,[H|T],[X,H|T]):- X<H.
insert(X,[H|T],[H|NT]):-X>H,insert(X,T,NT).

%sort(list l, list Acc, list rez)
%(i,i,o)
%return a new list rez -> l sorted
sort([],A,A).
sort([H|T],A,Sorted):-insert(H,A,NA),sort(T,NA,Sorted).

%inserSort(list l, list sorted)
%(i,o)
%wrapper function for sort
insertSort(List,Sorted):-sort(List,[],Sorted).