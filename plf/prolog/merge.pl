%merge(list l1, list l2, list rez)
%(i,i,o)
%l1, l2 are sorted -> rez merged l1 with l2
merge([],[],[]).
merge(X,[],X).
merge([],X,X).
merge([H1|T1],[H2|T2],[H1|NL]):-H1=H2, merge(T1,T2,NL).
merge([H1|T1],[H2|T2],[H1|NL]):-H1<H2, merge(T1,[H2|T2],NL).
merge([H1|T1],[H2|T2],[H2|NL]):-H1>H2, merge([H1|T1],T2,NL).