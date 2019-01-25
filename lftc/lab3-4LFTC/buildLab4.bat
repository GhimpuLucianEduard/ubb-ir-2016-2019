flex lab4.lx
bison -d grammar.y
gcc lex.yy.c grammar.tab.c -o l4