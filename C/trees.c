//Konstantinos Efkarpidis - icsd15051
//Nikolaos Apostolakis - icsd13012
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

struct Node * insert(struct Node * ,int);
void inorder(struct Node * );
void insert_many(struct Node *);
struct Node * deleteNode(struct Node * root, int value);
struct Node * minValueNode(struct Node* node);
int *number; //plithos stixion

struct Node {
    int value;
    struct Node * left;
    struct Node * right;
};
//n=5000 - xronos 0.046s
//n=10000 - xronos 0.061s
//n=15000 - xronos 0.109s
//n=20000 - xronos 0.155s
//n=30000 - xronos 0.187s
//Synolika prokeitai koini poliplokotita
//Otan exoume tyxaopioimenous arithmous i eisagogi exei O(logn) kai taxinomisi O(n) = O(nlogn)
//Antistoixa kai o quicksort

int main() {

    int choice;
    int num,more;
    number=&num;
    clock_t start, end;
    double total;
    
    struct Node *root = NULL; 
    
    do{
    do{
    printf("(1)Eisagogin N tuxaion arithmon se DDA & emfanisi periexomenon\n");
    printf("(2)Eisagogi neou arithmou apo to xristi\n");
    printf("(3)Diagrafi arithmou epilogis tou xristi\n");
    printf("(4)Emfanisi komvon DDA\n");
    printf("(5)Eksodos\n");
    printf("\n\nDose epilogi menu(1,2,3,4,5)");
    scanf("%d",&choice);
    start=clock();
    switch(choice){
        case 1:  root = insert(root,rand()%50001); insert_many(root); inorder(root); break;
        case 2:  printf("Dose arithmo\n"); scanf("%d",&num); insert(root,num); break;
        case 3:  printf("Dose arithmo\n"); scanf("%d",&num); deleteNode(root,num); break;
        case 4:  inorder(root); break;
        case 5:  return 0;
     }
    }while(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5);
    end=clock();
    total = ((double)(end-start))/CLOCKS_PER_SEC;
    printf("Xronos eketelesis gia %d stoixeia : %f\n",*number,total);
    printf("Ksana? (1=y)\n");
    scanf("%d",&more);
    }while(more==1);
    return 0;
}
//eisagogi polon
void insert_many(struct Node * root){
    int i,val;
    srand(time(NULL));
    printf("Dose arithmo komvon\n");
    scanf("%d",&(*number));
    for(i=0; i<*number-1; i++){
        val = rand()%50001;
            insert(root,val);
        }

}
//eisagogi neou kombou
struct Node * insert(struct Node * node,int value){
    
    struct Node *temp =  (struct Node *)malloc(sizeof(struct Node)); 
    temp->value = value; 
    temp->left = temp->right = NULL; 
    
    //periptosi adeiou dentrou
    if (node == NULL) 
            return temp; 
    //An i timi pros eisagogi einai mikroteri tou patera paei aristera allios dexia
    if (value < node->value) 
        node->left  = insert(node->left, value); 
    else if (value > node->value) 
        node->right = insert(node->right, value);    
  
   
    return node; 
    
}
// Emfanisi me diasxisi inorder
void inorder(struct Node *root){ 
   
    if (root != NULL){ 
        inorder(root->left); 
        printf("%d \n", root->value); 
        inorder(root->right); 
    } 
} 
//diagrafi komvou
struct Node * deleteNode(struct Node * root, int value){
    //an to dentro einai adio
    if (root == NULL) return root; 
  
    //an to stixeio pros diagrafei einai mikrotero apo to root
    //tote pigenei sto aristero paidi
    if (value < root->value) 
        root->left = deleteNode(root->left, value); 
  
    //allios sto dexi paidi
    else if (value > root->value) 
        root->right = deleteNode(root->right, value); 
  
    //se periptosi pou to stixeio gia diagrafi einai to root
    else{ 
        //i tha exei dexi paidi i katholou
        if (root->left == NULL) 
        { 
            struct Node * temp = root->right; 
            free(root); 
            return temp; 
        } 
        //i tha exei aristero i katholou
        else if (root->right == NULL){ 
            struct Node *temp = root->left; 
            free(root); 
            return temp; 
        } 
  
        //afou eftase edo exei dio paidia
        //epistrefei ton mikrotero
        struct Node* temp = minValueNode(root->right); 
  
        //antigrafo ton diadoxo 
        root->value = temp->value; 
  
        //svino komvo
        root->right = deleteNode(root->right, temp->value); 
    } 
    return root; 
}

//mpainei pros ta aristera tou komvou pou tou dosame
//mexri na ftasei se fullo
struct Node * minValueNode(struct Node* node) { 
    struct Node* current = node; 
  
    //anazitisi tou pio aristero fullou
    while (current->left != NULL) 
        current = current->left; 
  
    return current; 
} 
  


