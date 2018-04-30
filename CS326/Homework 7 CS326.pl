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
%union([],_,_).
%union(A,B,C) :- isSet(A) , isSet(B),

%Problem 1D

%Problem 2
tally(_,[],0).
tally(A,[A|T],N) :- tally(A,T,X), N is X + 1.
tally(A,[H|T],N) :- A \= H, tally(A,T,N).

%Problem 3
subst(_,_,[],[]).
subst(A,B,[A|T],[B|L1]) :- subst(A,B,T,L1).
subst(A,B,[H|T],[H|L1]) :- H \= A, subst(A,B,T,L1).

%Problem 4
insert(_,[],[]).
insert(A,[A|T],[A|L1]) :- insert(A,T,L1).
insert(A,[H|T],[H|L1]) :- H @< A, insert(A,T,L1).
%Problem 5
