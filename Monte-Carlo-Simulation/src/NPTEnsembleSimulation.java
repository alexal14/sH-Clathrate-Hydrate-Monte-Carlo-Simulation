/*****************************************************************************

    Physics Demos - Monte Carlo Simulation of sH Clathrate

    Copyright 20014, 2015 Alexander A. Atamas
    
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*****************************************************************************/

public class NPTEnsembleSimulation extends MetropolisEnsemble {

	NPTEnsembleSimulation(){
		
	}

	NPTEnsembleSimulation(final int numbMCSimulations){
		super(numbMCSimulations);
		
		freqOfSysOut = 2500;
	}
	
	@Override
	protected void engineMonteCarlo(int iMol, int iRan) {

	    if(iRan > 2*numbOfMolsActual){
	    	
		       enTot = volumeChange(molModelMap, 
		    		  			    molecules, 
	                                numbOfMolsActual,
	                                box,
	                                rcutSq,
	                                tableEnergyAllMoleciles,
	                                enTot
		    		  			   );
		    	
		    } else if (iRan < numbOfMolsActual){
		    	
				enTot = moleculeTranslation(iMol,
				 							molModelMap, 
				 							molecules, 
				 							numbOfMolsActual,
				 							box,
				 							rcutSq,
				 							tableEnergyAllMoleciles,
				 							enTot
										   );
		    	
		    } else {
		    	
				enTot = moleculeRotation(iMol,
		    			 				 molModelMap, 
		    			 				 molecules, 
		    			 				 numbOfMolsActual,
		    			 				 box,
		    			 				 rcutSq,
		    			 				 tableEnergyAllMoleciles,
		    			 				 enTot
		   								);		    	
		    }
	    
    
	    if ((stepMC % freqOfSysOut) == 0) {
	    	System.out.printf("i,E,box = %9d    %8.2f    %8.3f    %8.3f    %8.3f \n", stepMC, enTot / (double) numbOfMolsActual, box.x, box.y, box.z);
		}
		
	}
	
	protected void simulationRun(){
		
		simRun();		
	}
	

}
