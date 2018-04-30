; Clayton Franklin
; Homework 2
; 2/20/18
;
; Problem 1
{define (subst x y L)
  (cond 
        [ ( null? L ) '() ]
        [ ( equal? (car L) x ) (cons y (subst x y {cdr L } ) ) ]
        [ else ( cons (car L) (subst x y (cdr L) ) ) ]
        ) }
      
; Problem 2
{define (all-different? L)
  (cond
    [ (null? L) #t ]
    [ (member {car L} {cdr L} ) #f ]
    [ else (all-different? (cdr L) ) ]
    ) }
  
; Problem 3
{define T
    '[13
          (5
              (1 () ())
              (8 ()
                 (9 () ())))
          (22
              (17 () ())
              (25 () ()))]} 
; Problem 3 left T
{define (left T)
    (car (cdr T) ) }
      

; Problem 3 right T
{define (right T)
      (car (cdr (cdr T) ) )  }

; Problem 3 val T
{define (val T) 
    (car T) 
}

; Problem 3 n-nodes T 
{define (n-nodes T)
  (if (null? T) 0 
      (+ 1 (n-nodes (left T) ) 
             (n-nodes (right T) ) ) 
  ) }

; Problem 3 n-leaves T 
{define (n-leaves T)
  (cond 
        [ (null? T) 0 ]  
        [ (and (null? (left T) ) 
               (null? (right T) ) ) 1]
        [else (+ (n-leaves(left T) ) 
                 (n-leaves (right T) ) ) ]
  )
}
; Problem 3 height T 
{define (height T)
  (if (null? T) 0 
      [+ 1 (max (height (left T) ) (height (right T) ) ) ] ) }

; Problem 3 postorder T 
{define (postorder T)
  [if (null? T) '() 
   (append (postorder (left T) ) 
           (postorder (right T) ) 
           (list (val T ) ) 
   ) 
  ]
 }

; Problem 4
{define (flatten L)
  (cond
    [ (null? L) '()]
    [ (list? {car L} )
     (append {flatten (car L)} 
             {flatten (cdr L)} 
      ) 
    ]
    [ else (cons {car L} {flatten (cdr L)} ) ]
    )
}

; Extra Credit
{define (member-bst? V T)
  (cond
    [ (null? T) #f ]
    [ (= V (val T) ) #t]
    [ (> V (val T) ) (member-bst? V (right T) ) ]
    [ (< V (val T) ) (member-bst? V (left T) ) ]
    [ else #f ]
    ) }
 
 
 
 