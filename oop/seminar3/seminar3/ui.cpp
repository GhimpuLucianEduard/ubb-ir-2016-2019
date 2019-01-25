#include "ui.h"
#include <iostream>
#include <string>


void Ui::run()
{
	while (1)
	{
		int cmd = citesteComanda();
		if (cmd == 1)
		{
			string m="as";
			cin>>m;
			int n=0;
			cin>>n;
			ctr.add(m, n);
		}
	
			
	}

}

int main()

{
	return 0;
}