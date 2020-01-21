package dev.aielloparigino.tris.model;

import java.util.Optional;

//TODO: l'attuale algoritmo di checkWin passa le celle uno ad uno, equiparabile a InsertionSort,
//		controllare altri algortimi di sort (Selection sort?)

//TODO: usare java 8 stream reduce()? 
public class WinChecker {

	
	public static Integer checkWin(String[] gridArray) {
		
		//deduco griglia da array lenght
		//FIXME ha senso tenere lengh ? nei cicli spesso +1 / -1
		int lengh;
		if(gridArray.length == 9) {
			lengh = 3;
		}else if(gridArray.length == 25) {
			lengh = 5;
		}else {
			lengh = 10;
		}
			
		Integer result = null;
		result = Optional.ofNullable(checkHorizontaly(gridArray, lengh)).isPresent() ? checkHorizontaly(gridArray, lengh) : result;
		result = Optional.ofNullable(checkVertically(gridArray, lengh)).isPresent() ? checkVertically(gridArray, lengh) : result;
		result = Optional.ofNullable(checkCross(gridArray, lengh)).isPresent() ? checkCross(gridArray, lengh) : result;
		
		return result;
	}

	private static Integer checkHorizontaly(String[] gridArray, int lengh) {
		
		for(int i=0; i < gridArray.length; ) {
			for(int x=i+1; x < gridArray.length;x++) {
				//prima check su placeholder, inutile procedere
				if( (gridArray[i].equals("_")) || (!(gridArray[i].equals(gridArray[x]))) ) {
					//mi interessa tutti uguali al primo della riga, transitivo uguali tra tutti
					break;
				}else {
					//trovata una riga intera con stessi valori -> vittoria
					if(x == i+(lengh-1))
						return Integer.valueOf(gridArray[i]);
				}
			}
			//FIXME shorthand +=
			i= i+lengh;
		}
		return null;
	}
	
	private static Integer checkVertically(String[] gridArray, int lengh) {
		
		for(int i=0; i < lengh;i++ ) {
			for(int x=i+lengh; x < gridArray.length;) {
				//prima check su placeholder, inutile procedere
				if( (gridArray[i].equals("_")) || (!(gridArray[i].equals(gridArray[x]))) ) {
					//mi interessa tutti uguali al primo della riga, transitivo uguali tra tutti
					break;
				}else {
					//trovata una riga intera con stessi valori -> vittoria
					if(x == i+((lengh*lengh)-lengh))
						return Integer.valueOf(gridArray[i]);
				}
				//FIXME shorthand +=
				x= x+lengh;
			}
			
		}
		return null;
	}
	
	
	//FIXME code smell: cicli troppo simili
	private static Integer checkCross(String[] gridArray, int lengh) {
		
		for(int x=lengh+1; x < gridArray.length;) {
			//prima check su placeholder, inutile procedere
			if( (gridArray[0].equals("_")) || (!(gridArray[0].equals(gridArray[x]))) ) {
				//mi interessa tutti uguali al primo della riga, transitivo uguali tra tutti
				break;
			}else {
				//trovata una riga intera con stessi valori -> vittoria
				if(x == gridArray.length-1)
					return Integer.valueOf(gridArray[0]);
			}
			//FIXME shorthand +=
			x= x+(lengh+1);
		}
		
		for(int x=lengh+1; x < gridArray.length;) {
			//prima check su placeholder, inutile procedere
			if( (gridArray[lengh-1].equals("_")) || (!(gridArray[lengh-1].equals(gridArray[x]))) ) {
				//mi interessa tutti uguali al primo della riga, transitivo uguali tra tutti
				break;
			}else {
				//trovata una riga intera con stessi valori -> vittoria
				if(x == gridArray.length-lengh)
					return Integer.valueOf(gridArray[lengh-1]);
			}
			//FIXME shorthand +=
			x= x+(lengh-1);
		}
		
		
		return null;
	}
}
