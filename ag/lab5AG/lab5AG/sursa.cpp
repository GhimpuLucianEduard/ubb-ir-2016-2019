#include <iostream>
#include <fstream>
using namespace std;
int i, j, k, a, b, u, v, n, ne = 1;
int min, mincost = 0, parent[9];
int find(int);
int uni(int, int);



int cost[4][4] =
{
	{0 ,10, 11, 0},
	{10, 0, 12, 27 },
	{11, 12, 0, 0},
	{0, 27, 0, 0 },

};


int uni(int i, int j)
{
	if (i != j)
	{
		parent[j] = i;
		return 1;
	}
	return 0;
}
int find(int i)
{
	while (parent[i])
		i = parent[i];
	return i;
}

int main()
{


	printf("\n\tImplementation of Kruskal's algorithm\n");
	printf("\nEnter the no. of vertices:");
	cin >>n;
	
	
	for (i = 1; i <= n; i++)
	{
		for (j = 1; j <= n; j++)
		{
			cin >> cost[i][j];
			if (cost[i][j] == 0)
				cost[i][j] = 999;
		}
	}
	cout<<"The edges of Minimum Cost Spanning Tree are\n";
	while (ne < n)
	{
		for (i = 1, min = 999; i <= n; i++)
		{
			for (j = 1; j <= n; j++)
			{
				if (cost[i][j] < min)
				{
					min = cost[i][j];
					a = u = i;
					b = v = j;
				}
			}
		}
		u = find(u);
		v = find(v);
		if (uni(u, v))
		{
			cout << ne++ << ",edge ("<<a<<","<<b<<") ="<<min<<"\n";
			mincost += min;
		}
		cost[a][b] = cost[b][a] = 999;
	}
	printf("\n\tMinimum cost = %d\n", mincost);

	//getch();
	return 0;
}
