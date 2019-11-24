//With Component State counter
class Counter extends React.Component {
    constructor(props){
        super(props);
        this.handleAddOne = this.handleAddOne.bind(this)
        this.handleMinusOne = this.handleMinusOne.bind(this)
        this.handleReset = this.handleReset.bind(this)
        this.state= {
            count: 0
        };
    }

    handleAddOne(){
        this.setState((prevState) => {
            return {
                count: prevState.count + 1
            };
        });
    }

    handleMinusOne(){
        this.setState((prevState) => {
            return {
                count: prevState.count - 1
            };
        });
    }

    handleReset(){
        this.setState(() => {
            return {
                count: 0
            };
        });
    }

    componentDidMount() {
        const stringCount = localStorage.getItem('count');
        const count = parseInt(stringCount,10)

        if (!isNaN(count)){
            this.setState ( () => ({count}))
        }
    }

    componentDidUpdate(prevProps,prevState) {
        if (prevState.count !== this.state) {
            localStorage.setItem('count',this.state.count)
        }
            
    }

    render(){
        return (
            <div>
            <h1>Count: {this.state.count}</h1>
            <button onClick={this.handleAddOne}>+1</button>
            <button onClick={this.handleMinusOne}>-1</button>
            <button onClick={this.handleReset}>Reset</button>
            </div>
        );
    }
}


ReactDOM.render(<Counter/>,document.getElementById('app'));


//Without Component State Counter
// let count = 0;

// const addOne = () => {
//     count++;
//     console.log('addOne Called');
//     renderCounter();
// };

// const minusOne = () => {
//     count--;
//     console.log('minusOne Called');
//     renderCounter();
// };

// const resetOne = () => {
//     count=0;
//     console.log('resetOne Called');
//     renderCounter();
// };

// const appRoot = document.getElementById('app');

// const renderCounter = () => {
//     const templateTwo = (
//         <div>
//             <h1>Count: {count}</h1>
//             <button id="addButton" onClick={addOne} className="button">Add</button>
//             <button id="minusButton" onClick={minusOne} className="button">Minus</button>
//             <button id="resetButton" onClick={resetOne} className="button">Reset</button>
//         </div>
//     );
//     ReactDOM.render(templateTwo,appRoot);
// }

// renderCounter();