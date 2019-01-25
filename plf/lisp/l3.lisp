#|
				  { 0, daca l1 e atom si diferit de e	
	membru(e,l) = { 1, daca l1 e atim si = cu e
				  { membru(e,l1) U memebru(e,l2) ... U membru(e,ln)
	
	
	sum(l) = { l1, daca l1 e atom
			 { sum(l1) + sum(l2) + ... sum(ln)


|#

(defun membru(e l)
	(cond 
    	((and (atom l) (not (equal l e))) 0)
    	((and (atom l) (equal l e)) 1)
    	(t (mapcar #'(lambda (a) (membru e a)) l))
	)
)


(defun sum (x)
	(cond
		((atom x) x)
		(t (apply '+ (mapcar #'sum x)))
	)
)

(defun wrapper (l e)
	(sum (membru e l))	
)

