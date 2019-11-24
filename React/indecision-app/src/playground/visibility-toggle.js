//With State machine
class Visibility extends React.Component{
    constructor(props){
        super(props);
        this.handleState = this.handleState.bind(this);
        this.state = {
            visibility: false
        }
    }

    handleState(){
        this.setState((prevState) => {
            return {
                visibility: !prevState.visibility
            };
        })
    }

    render(){
        return(
            <div>
                <h1>Visibility Toggle!</h1>
                <button onClick={this.handleState}>{this.state.visibility ? 'Hide details' : 'Show details'}</button>
                {this.state.visibility && (
                    <div>
                        <p>Hey. These are some details you can now see</p>
                    </div>
                ) }
            </div>
        );
        
    }
}

ReactDOM.render(<Visibility/>,document.getElementById('app'));


// Without State technique
// const details = {
//         title: 'Visibility toggle',
//         details: 'Hey, These are some details you can now see!',
//         flag: false
// };

// const changeFlag = () => {
//     details.flag = !details.flag;
//     renderTemplate();
// };

// const renderTemplate = () => {
//     const template = (
//         <div>
//             <h1>{details.title}</h1>
//             <button onClick={changeFlag}>{details.flag ? 'Show details' : 'Hide details'}</button>
//             <p>{details.flag ? '' : details.details}</p>
//         </div>
//     );
//     const appRoot = document.getElementById('app');
//     ReactDOM.render(template,appRoot);
// };

// renderTemplate();
