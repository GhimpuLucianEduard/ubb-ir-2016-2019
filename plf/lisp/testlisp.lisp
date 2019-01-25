(defun adauga(lista n nactual element)
	(cond 
		((= 0 n) lista)
		((null lista) nil)
		((= n nactual) (append (list(car lista )) (list element) (adauga (cdr lista) n (+ 1 nactual) element)))
		((= 0 (mod nactual (* 2 n))) (append (list(car lista )) (list element) (adauga (cdr lista) n (+ 1 nactual) element)))
		(t (append (list (car lista) ) (adauga (cdr lista) n (+ 1 nactual) element)) )
	)
)

(defun wrapper(lista n element)
	(adauga lista n 1 element)
)

#|
adauga(lista,n,nactual,element) = { null, daca lista vida
								  { l1 U element U adauga(l2...ln,n,nactual+1,elemet), daca nactual = n
								  { l1 U element U adauga(l2...ln,n,nactual+1,element), daca nactual % 2*n == 0
								  { adauga(l2..ln,n,nactual+1,element), altfel

								  	|#