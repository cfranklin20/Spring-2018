% Clayton Franklin
% CS 326 Homework 7
% April 30, 2018

% Problem 1A
isSet([_]).
isSet([H | T]) :- not( member(H,T) ) , ( isSet( T ) ).

%Problem 1B
subset([],_).
subset([H | T] , Y) :- isSet([H | T]) , isSet(Y) , member(H,Y), subset(T,Y).

%Problem 1C
union([],_,_).
union(A,B,C) :- subset(A,C) , subset(A,B), append(A,B,List), subset(C,List), isSet(C).

%Problem 1D
intersection([],_,[]).
intersection(_,[],[]).
intersection([H|T], B, [C|V]) :- member(H, B), C is H, intersection(T, B, V).
intersection([H|T], B, C) :- not(member(H,B)), intersection(T, B, C).

%Problem 2
tally(_,[],0).
tally(A,[A|T],N) :- tally(A,T,X), N is X + 1.
tally(A,[_|T],N) :- tally(A,T,N).

%Problem 3
subst(_,_,[],[]).
subst(A,B,[A|T],[B|L1]) :- subst(A,B,T,L1).
subst(A,B,[H|T],[H|L1]) :- H \= A, subst(A,B,T,L1).

%Problem 4
insert(A,[],[A]).
insert(A,[H|T],[R|Q]) :- H < A, R is H, insert(A,T,Q).
insert(A,[H|T],[R|Q]) :- H > A, R is A, append([H],T,Q).

%Problem 5
flatten([],[]).
flatten([H|T],L) :- flatten(H,D), flatten(T,E), append(D,E,L).
flatten(A,[A]).
