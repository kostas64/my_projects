class IndecisionApp extends React.Component{
    constructor(props){
        super(props);
        this.handleDeleteOptions = this.handleDeleteOptions.bind(this);
        this.handlePick = this.handlePick.bind(this);
        this.handleAddOption = this.handleAddOption.bind(this);
        this.handleDeleteOption = this.handleDeleteOption.bind(this);
        this.state = {
            options: []
        };
    }

    componentDidMount() {
        try {
            const json = localStorage.getItem('options');
            const options = JSON.parse(json);

            if (options){
                this.setState(() => ({options}))
            };
        }catch(e) {
            //Do nothing at all
        }  
    }

    componentDidUpdate(prevProps,prevState) {
        if (prevState.options.length !== this.state.options.length) {
            const json = JSON.stringify(this.state.options)
            localStorage.setItem('options', json)
        } 
    }

    componentWillUnmount() {
        console.log('Component will unmount')
    }

    handleDeleteOptions() {
        //  () after arrow -> return object 
        // instead with {} -> function body      
        this.setState(() => ({ options: [] }));
    }

    handleDeleteOption(optionToRemove){
        this.setState((prevState) => ({
            options: prevState.options.filter((option) => optionToRemove !== option)
        }))
    }

    handlePick() {
        this.setState(() => {
            const randNum = Math.floor((Math.random() * this.state.options.length));
            const opt =  this.state.options[randNum];
            alert (opt)
        });
    }

    handleAddOption(option) {
        if (!option){
            return 'Enter valid value'
        }else if (this.state.options.indexOf(option) > -1 ){
            return 'This option already exists'
        }else {
            this.setState((prevState)=> ({
                options: prevState.options.concat(option) 
            }))
        }
    }
   
    render(){
        const subtitle = "..."
        return (
            <div>
            <Header 
                subtitle={subtitle}
            />
            <Action 
                hasOptions={this.state.options.length > 0}
                handlePick={this.handlePick} 
            />
            <Options 
                options={this.state.options}
                handleDeleteOptions={this.handleDeleteOptions}
                handleDeleteOption={this.handleDeleteOption}
            />
            <AddOption 
                handleAddOption={this.handleAddOption}
            />
            </div>
        );
    }
}


const Header = (props) => {
        return (
            <div>
                <h1>{props.title}</h1>
                {props.subtitle && <h2>{props.subtitle}</h2>}
            </div>
        );
} 

Header.defaultProps= {
    title: 'Indecision'
}

const Action = (props) => {
        return (
            <div>
            <button 
            onClick={props.handlePick}
            disabled={!props.hasOptions}
            >What should I do?</button>
            </div>
        );
}


const Options = (props) => {
        return (
            <div>
                <button onClick={props.handleDeleteOptions}>Remove All</button>
                {props.options.length === 0 && <p>Please add an option</p>}
                <ol>
                {
                    props.options.map((opts) => (
                        <Option 
                        key={opts} 
                        option={opts}
                        handleDeleteOption={props.handleDeleteOption}
                        />
                    ))
                }
                </ol>
            </div>
        );
}

const Option = (props) => {
        return (
            <div>
               {props.option}
               <button 
                    onClick={(e) => {
                        props.handleDeleteOption(props.option)
                    }}
               >
                Remove
               </button>
            </div>
        );   
}

class AddOption extends React.Component{
    constructor(props){
        super(props);
        this.handleAddOption = this.handleAddOption.bind(this)
        this.state = {
            error : undefined
        }
    }

    handleAddOption(e){
        e.preventDefault();
        const option = e.target.elements.addValue.value.trim();
        const error = this.props.handleAddOption(option);
        this.setState(() => ({ error })) 
        if (!error){
            e.target.elements.addValue.value = '';
        }   
    }

    render(){
        return (
            <div>
              {this.state.error && <p>{this.state.error}</p>}
               <form onSubmit={this.handleAddOption}>
                <input type="text" name="addValue"/>
                <button>Add Item</button>
               </form>
            </div>
        );
    }
}

ReactDOM.render(<IndecisionApp/>,document.getElementById('app'));