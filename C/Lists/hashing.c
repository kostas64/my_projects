//Konstantinos Efkarpidis - icsd15051
//Nikolaos Apostolakis - icsd13012
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#define m 23

struct Node
{
    char word[25];
    int counter;
    struct Node *next;
};

//Diloseis
int collisions = 0;
struct Node * hash_t[m];
void InitializeHashTable();
int getkey(char []);
int hash_key(int );
void insert_word (char [],struct Node * []);
void print();
void printUnique();

int main()
{

    FILE *fp;
    fp = fopen("data3.txt","r");
    char word[20];
    clock_t start, end;
    double elapsed,sz;


    //arxikopoisi pinaka
    InitializeHashTable();
    start = clock();
    while(fscanf(fp,"%s",&word)==1)
    {

        insert_word(word,hash_t);

    }
    end = clock();
    elapsed = ((double) (end - start)) / CLOCKS_PER_SEC;
    printf("\n--------------Arxikos Pinakas------------\n");
    print();
    printf("\n--------Pinakas Monadikon Lexeon-----------\n");
    printUnique();
    printf("\n------------Megethos & Xronos ektelesis-------\n");
    //Metrisi megethous arxeioy
    fseek(fp, 0L, SEEK_END);
    sz = ftell(fp);
    printf("  %lf         %.2lf KBytes",elapsed,sz/1024);
    printf("\n-------------Sunolika collisions-------------\n");
    printf("  %d",collisions);


}

void InitializeHashTable()
{
    int i;
    for(i=0; i<m; i++)
    {
        hash_t[i]=NULL;
    }

};

void insert_word (char word[],struct Node * hash_t[m])
{

    struct Node *temp,*nd,*previous;
    int length,index,key;
    key = getkey(word);
    index= hash_key(key);
    length=strlen(word);
    nd = (struct Node *)malloc(sizeof(struct Node));
    //adeio head gia ekeino to key
    if(hash_t[index]==NULL)
    {

        nd->counter = 0;
        //periptosi poy den exei kapoio sumbolo sto telos
        if(word[length-1]!=33 && word[length-1]!=46 && word[length-1]!=63 && word[length-1]!=44 && word[length-1]!=59)
        {
            strcpy(nd->word,word);
            nd->counter = 1;
            nd->next = NULL;
            hash_t[index]=nd;

        }
        //antikatastasi simboloy
        else
        {
            word[length-1]='\0';
            strcpy(nd->word,word);
            nd->counter = 1;
            nd->next = NULL;
            hash_t[index]=nd;

        }
    }
    //collision
    else
    {
        collisions+=1;
        temp = hash_t[index];
        previous = temp;
        //epanalipsi gia taxinomisi
        while(strcmp(temp->word,word)<0 && temp->next!=NULL)
        {
            previous=temp;
            temp = temp -> next;
        }
        //euresu idias lexeis
        if(strcmp(temp->word,word)==0)
        {
            temp->counter = temp-> counter + 1;
        }
        //periptosi eisagogis lexis sto head
        else if(previous == temp && strcmp(temp->word,word)>0 )
        {

            if(word[length-1]!=33 && word[length-1]!=46 && word[length-1]!=63 && word[length-1]!=44 && word[length-1]!=59)
            {
                nd->next=hash_t[index];
                strcpy(nd->word,word);
                hash_t[index]=nd;
                nd->counter = 1;
            }
            else
            {

                word[length-1]='\0';
                nd->next=hash_t[index];
                strcpy(nd->word,word);
                hash_t[index]=nd;
                nd->counter = 1;
            }
        }
        //eisagogi sto telos
        else if (temp->next == NULL && strcmp(temp->word,word)<0 )
        {
            if(word[length-1]!=33 && word[length-1]!=46 && word[length-1]!=63 && word[length-1]!=44 && word[length-1]!=59)
            {
                temp-> next = nd;
                nd-> next = NULL;
                strcpy(nd->word,word);
                nd ->counter = 1;
            }
            else
            {
                word[length-1]='\0';
                temp-> next = nd;
                nd-> next = NULL;
                strcpy(nd->word,word);
                nd ->counter = 1;
            }
        }
        //eisagogi endiamesa
        else if(temp->next!=NULL && strcmp(temp->word,word)>0)
        {
            if(word[length-1]!=33 && word[length-1]!=46 && word[length-1]!=63 && word[length-1]!=44 && word[length-1]!=59)
            {
                nd->next= temp;
                previous->next = nd;
                strcpy(nd->word,word);
                nd->counter = 1;
            }
            else
            {
                word[length-1]='\0';
                nd->next= temp;
                previous->next = nd;
                strcpy(nd->word,word);
                nd->counter = 1;
            }
        }

    }

}
//sunartisi ypologismou klidiou
int getkey(char word[])
{

    int i,length,sum;
    length = strlen(word);

    if(word[length-1]==33 || word[length-1]==46 || word[length-1]==63 || word[length-1]==44 || word[length-1]==59)
        word[length-1]='\0';

    for (i=0; i<length; i++)
        sum = sum + (int)word[i];

    return sum;
}
//sunartisi upologismoy klidiou
int hash_key(int key)
{
    return key%m;
}

//emfanisi telikoy pinaka
void print()
{
    int i;
    struct Node * temp;
    for(i=0; i<m; i++)
    {
        printf("\n");
        temp = hash_t[i];

        if(temp!=NULL)
        {
            while(temp!=NULL)
            {
                printf("%s %d ",temp->word,temp->counter);
                temp=temp->next;
            }
        }
    }
     printf("\n");

}
//emfanisi monadikon lexeon
void printUnique(){

int i;
    struct Node * temp;
    for(i=0; i<m; i++)
    {
        printf("\n");
        temp = hash_t[i];

        if(temp!=NULL)
        {
            while(temp!=NULL)
            {
                if(temp->counter == 1){
                     printf("%s %d ",temp->word,temp->counter);
                }

                temp=temp->next;
            }
        }
    }
     printf("\n");

}


