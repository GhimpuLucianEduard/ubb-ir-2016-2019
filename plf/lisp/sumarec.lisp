(defun suma ( l )
 (cond
 	 ((null l) 0)
 	 ((numberp l) (+ l))
 	 (t (+  (car l) (suma (cdr l) ) ) )
 	)
)

(defun sumaaux (l)
	(suma l)
)