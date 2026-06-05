// import React, {Component} from 'react';

// class CardSet extends Component{
//     constructor(){
//         super();
//         this.state = {
//             chosenCards: []
//         }
//     }
//     render(){
//         console.log(this.props.cards)

//         const cardList = this.props.cards.map((card) =>{
//             return(
//                 <div className="col s2">
//                     <div className="card hoverable small">
//                         <div className="card-image">
//                             <img src={card.temporalImage} />
//                         </div>
//                             <div className="card-content">
//                             <p>{card.name}</p>
//                             <p>{card.city}</p>
//                         </div>
//                             <div className="card-action">
//                             <a href="#">$109.99</a>
//                         </div>
//                     </div>
//                 </div>
//             )
//         })


//         return(
//             <div>
//                 {cardList}
//             </div>
//         )
//     }
// }

// export default CardSet;








import React, {Component} from 'react';
import './CardSet.css';

class CardSet extends Component{
    constructor(){
        super();
        this.state = {
            favorites: []
        }
    }

    toggleFavorite = (card) => {
        const isFavorite = this.state.favorites.includes(card.id);
        
        if (isFavorite) {
            const newFavorites = this.state.favorites.filter(id => id !== card.id);
            this.setState({ favorites: newFavorites });
        } else {
            this.setState({ favorites: [...this.state.favorites, card.id] });
        }
    }

    render(){
        const cardList = this.props.cards.map((card) =>{
            const isFavorite = this.state.favorites.includes(card.id);
            
            return(
                <div className="col s2" key={card.id}>
                    <div className="card hoverable small">
                        <div className="card-image" style={{position: 'relative'}}>
                            <img src={card.temporalImage} alt={card.name} />
                            
                            {/* Favorite button */}
                            <button 
                                onClick={() => this.toggleFavorite(card)}
                                className= "favorite-btn"
                            >
                                {isFavorite ? (
                                    // Pink filled heart
                                    <svg width="28" height="28" viewBox="0 0 24 24" fill="#ff4081" stroke="#ff4081" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                                        <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path>
                                    </svg>
                                ) : (
                                    // White outline heart
                                    <svg width="28" height="28" viewBox="0 0 24 24" fill="none" stroke="white" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                                        <path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"></path>
                                    </svg>
                                )}
                            </button>
                        </div>
                        <div className="card-content">
                            <p>{card.name}</p>
                            <p>{card.city}</p>
                        </div>
                        <div className="card-action">
                            <a href="#">$109.99</a>
                        </div>
                    </div>
                </div>
            )
        })

        return(
            <div>
                {cardList}
            </div>
        )
    }
}

export default CardSet;