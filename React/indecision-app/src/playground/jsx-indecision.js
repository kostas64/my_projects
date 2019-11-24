console.log('App.js is running');

//if statements
//operators

//only render the subtitle (and p tag) if subtitle exist 
//logical and operator
//render new p tag - if options.length >0 "Here are your options", "No options"

//App object
const app = {
    title: 'Indecision App',
    subtitle: 'This is  the subtitle',
    options: ['Pipis','Giorgis']
};

const onFormSubmit = (e) => {
    //Prevent page from refresh (GET passing argu on URL)
    e.preventDefault();
    
    const option = e.target.elements.option.value;
    if (option){
        app.options.push(option);
        e.target.elements.option.value = '';
        renderValue();
    }
};


const onMakeDecision = () => {
    const randNum = Math.floor((Math.random() * app.options.length));
    const option = app.options[randNum];
    alert(option);
};

const renderValue = () => {
    const template = (
        <div>
           <h1> {app.title}</h1>
           {app.subtitle && <p>{app.subtitle}</p>}
           <p>{app.options.length > 0 ? 'Your options' : 'No options'}</p>

           <button disabled={app.options.length === 0} onClick={onMakeDecision}>What should I do</button>
           <button id="rmvButton" onClick={rmvArrayEls}>Remove all</button>
           <ol>{
               [app.options.map((opt) => <li key={opt}>{opt}</li>)]
           }
           </ol>
           <form onSubmit={onFormSubmit}>
                <input type="text" name="option"/>
                <button>Add</button>
           </form>
         </div>
        ); 
        ReactDOM.render(template,appRoot);
};

const rmvArrayEls = () => {
    app.options = [];
    renderValue(); 
};


const appRoot = document.getElementById('app');

renderValue();












