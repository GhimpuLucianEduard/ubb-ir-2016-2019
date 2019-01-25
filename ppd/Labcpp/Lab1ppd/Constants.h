#pragma once
#include <iostream>
#include <string>

using namespace std;

#define PRINT_S(x) cout << x << cout << " ";
#define PRINT_L(x) cout << x << cout << endl;
#define PRINT_NL cout << endl;

#define SPACE_STRING " "
#define EMPTY_STRING ""
#define NEW_LINE "\n"

namespace Constants
{
	static int ROW_COUNT;
	static int COL_COUNT;
	static int THREAD_COUNT;
	static enum dataType { INT = 1, FLOAT = 2, COMPLEX = 3 };
};


