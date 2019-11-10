//Nikolaos Apostolakis - icsd13012
//Konstantinos Efkarpidis - icsd15051

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


struct Node
{
    char *word;
    char *meaning;
    struct Node * next;
    struct Node * previous;
};


void createdict (FILE *,struct Node *[]);
void AddNodeDouble(struct Node **,char * ,char *,int );

int main()
{

    int choice,i;
    struct Node *ptr[26];
   

    for(i=0; i< 26; i++){
       ptr[i] = NULL;
    }

    FILE *file;
    file = fopen("lexiko.txt", "r");

    printf("(1)Anagnosi arxeiou\n");
    printf("(2)Emfanisi ermhnias gia lexi tis epilogis sas\n");
    printf("(3)Eisagogi neas lexis sto lexiko\n");
    printf("(4)Diagrafi lexis apo to lexiko\n");
    printf("(5)Diagrafi lexeon gia sugkekrimen o arxiko gramma\n");
    printf("(6)Emfanisi lexeon apo to lexiko gia sygkekrimeno gramma\n");
    printf("(7)Exodos\n");

    do
    {
        printf("Arxika epilexe 1 gia eisagogi lexikou\n");
        scanf("%d",&choice);
    }while(choice!=1);

    switch(choice)
    {
    case 1:
        createdict(file,ptr);
        printf("Eftase exo");
        break;
     //case 2: display_inter(word); break;

    }

    return 0;
}

void  createdict (FILE *file,struct Node *ptr[]){
    char line[100];
    char *word;
    char *meaning;
    struct Node *temp,**tail = (struct Node **)malloc(sizeof(struct Node));
    struct Node *nd ;
    
    
    
    int i;
    while(fgets(line,100,file)){
       
        word=strtok(line,":");
        meaning=strtok(NULL,":");
        
        i = (int)word[0]-97;
        nd = (struct Node *)malloc(sizeof(struct Node));
        temp = (struct Node *)malloc(sizeof(struct Node));
        nd->word = strdup(word);
        nd->meaning = strdup(meaning);
        
         
        if(ptr[i]==NULL){
            nd->next=NULL;
            nd->previous=NULL;
            ptr[i]=nd; 
        }else{
        
            temp=ptr[i];
            *tail = temp;
            int counter=0;
            while(temp!=NULL && strcmp(temp->word,nd->word)<0 ){
                counter++;
                *tail = temp;
                temp=temp->next;
            }
           
             if(temp==NULL){
                AddNodeDouble(tail,word,meaning,-1);
                printf("%s ",(*tail)->word);
            } else if(strcmp(temp->word,nd->word)>0){
                temp->previous->next = nd;
                nd->previous = temp->previous;
                nd->next=temp;
                temp->previous=nd;
            }
        }
        
       
    }
  
  
}

void AddNodeDouble(struct Node ** tail,char * word,char *meaning,int pos){

    struct Node *nd = (struct Node *)malloc(sizeof(struct Node));
    nd->next=nd->previous=NULL;
    nd->word=strdup(word);
    nd->meaning=strdup(meaning);

     
        if(pos==-1){
        (*tail)->next=nd;
        nd->previous=*tail;
        *tail=nd;
        }
    
  
}
