//Konstantinos Efkarpidis - icsd15051
//Nikolaos Apostolakis - icsd13012
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define m 20
//diloseis
int * create_rand_arr();
int * sort(int *);
int * heapify(int *);
void  adjust(int *,int);
int * add_new(int ,int *,int);
void display(int *);
int counter=0;
int main(){

    int choice,num;
    int *ptr1,*ptr2;
    int b[100];
    ptr2=b;
    srand(time(NULL));

    printf("(1)Dimiourgia pinaka tuxaion\n");
    printf("(2)Dimiourgia sorou apo pinaka\n");
    printf("(3)Prosthiki neou stixiou stin soro (apo xristi)\n");
    printf("(4)Emfanisi sorou\n");
    printf("(5)Taksinomisi kai emfanisi sorou\n");
    printf("(6)Eksodos");
    do{
    printf("\nDose epilogi menu(1,2,3,4,5,6)");
    scanf("%d",&choice);
    switch(choice){
        case 1: ptr1 = create_rand_arr(); break;
        case 2: ptr2 = heapify(ptr1); break;
        case 3: printf("Dose noumero gia eisagogi"); scanf("%d",&num); ptr2 = add_new(num,ptr2,counter); break;
        case 4: display(ptr2); break;
        case 5: ptr2 = sort(ptr2); break;
        case 6: return 0;
     }
    }while(choice!=6);


}
//dimiourgia pianaka tuxaion arithmon
int * create_rand_arr(){

    int i;
    static int arr[m];

    for(i=0; i<m; i++)
    {
        arr[i] = 1 + rand()%100;
    }
    return arr;
}
//taksinomisi
int * sort(int * a){

    int i,swap;

	for (i=m-1;i>0;i--) {
		swap = *a;
		*a = *(a+i);
		*(a+i) = swap;
		adjust(a,i);
	}
    return a;
}
//dimiourgia sorou
int * heapify(int *a) {
	int k,i,j,item;
	for (k=1;k<m;k++) {
		item = *(a+k);
		i = k;
		j = (i-1)/2;
		while((i>0)&&(item>a[j])) {
			*(a+i) = *(a+j);
			i = j;
			j = (i-1)/2;
		}
		counter+=1;
		*(a+i) = item;

	}
	return a;
}
//taksinomisi sorou megistou
void adjust(int *a,int n) {
	int i,j,item;
	j = 0;
	item = *(a+j);
	i = 2*j+1;
	while(i<=n-1) {
		if(i+1 <= n-1)
		   if(*(a+i) < *(a+i+1))
		    i++;
		if(item< *(a+i)) {
			*(a+j) = *(a+i);
			j = i;
			i = 2*j+1;
		} else
		   break;
	}
	*(a+j) = item;
}
//prosthiki neou antikimenou
int * add_new(int num,int *a,int counter){
    counter=counter+1;
   *(a+counter) = num ;
   int i=counter;
   int parent = i/2;
   int swap;
        while(i>1 && *(a+parent)<*(a+i)){
            swap = *(a+parent);
            *(a+parent)=*(a+i);
            *(a+i)=swap;
            i=parent;
            parent=i/2;
        }
return a;
}
//provoli sorou
void display(int * a){
    int i;
    for(i=0; i<m; i++)
        printf(" %d" ,*(a+i));
}
