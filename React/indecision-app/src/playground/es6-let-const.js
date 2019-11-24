var nameVar = 'Andrew';
var nameVar = 'Mike';
console.log('nameVar',nameVar);

let nameLet = "Jen";
 nameLet = "July";
console.log('nameLet',nameLet);

const nameConst = 'Kostas';
console.log('nameConst',nameConst);

//Block scoping
const fullName = 'Giorgos Mead';
let firstName;

if (fullName){
     firstName = fullName.split(' ')[0];
    console.log(firstName);
}

console.log(firstName);