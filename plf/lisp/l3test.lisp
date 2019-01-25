(DEFUN triple (x e)
(COND
((numberp x) (* x e))
((atom x) x)
(t (mapcar #'(lambda (a) (triple a e)) x))
)
)
