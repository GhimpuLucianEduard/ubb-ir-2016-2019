#| MODEL MATEMATIC
					 			   { null, daca lista vida
	drept(l1...ln,noduri,muchii) = { null, daca noduri = muchii+1
								   { l1 U l2 U drept(l3...ln,noduri+1,muchii+l2)

								   { null, daca lista vida
	stang(l1...ln,noduri,muchii) = { l1...ln, daca noduri = muchii+1
								   { stang(l3...ln,nocuri+1,muchii+l2)

							  { null, daca lista vida
	wrapper(l1...ln, nivel) = { l1, daca nivel = 0
							  {	wrapper(drept(l3...ln,0,0),nivel-1) U wrapper(stang(l3...ln,0,0),nivel-1)
|#


(defun drept(lista noduri muchii)
   (cond
      	((null lista) nil)
      	((= noduri (+ 1 muchii))  nil)
      	(t (append (list (car lista)) (list (cadr lista))
         	(drept (cddr lista) (+ noduri 1) (+ muchii (cadr lista))))
     	)
   )
) 

(defun stang(lista noduri muchii)
	   (cond
		     ((null lista) nil)
		     ((= noduri (+ 1 muchii)) lista)
		     (t (stang (cddr lista) (+ noduri 1) 
		        (+ muchii (cadr lista)))
		     )
	   )
)

(defun wrapper(lista nivel)
   (cond
	     ((null lista) nil)
	     ((= nivel 0) (list (car lista)))	
	     (t (append  (wrapper (drept (cddr lista) 0 0) (- nivel 1))
	              (wrapper (stang (cddr lista) 0 0) (- nivel 1))) 
	     )
   )
)


problema 5 la l3
