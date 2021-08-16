import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class Game1 
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		//Asks if player wants to play the game
		System.out.println("Would you like to play David and the Goliath?(Y/N)");
		String userInput = input.nextLine();
		
		//If user inputs Y\y, user plays game, else, terminates program
		if(userInput.equals("Y") || userInput.equals("y"))
		{
			System.out.println("Let's play David and the Goliath");
			System.out.println();
			Game2();
		}
		else
		{
			System.out.println("Exiting Game");
			System.exit(0);
		}	
	}
	
	public static void Game2() 
	{
		Giant goliath = new Giant();
		Human david = new Human();
		
		int damage_dealt;
		int damage_taken;
		int crit;
		int item_count_atk = 2;
		int item_count_def = 2;
		int atkp_turns = 0;
		int defp_turns = 0;
		boolean atkp = false;
		boolean defp = false;
		boolean berStan = false;
		//Boss statistics
		int b_MAXHP = goliath.hp() * 100;
		int b_HP = goliath.hp() * 100;
		int b_Def = goliath.def() * 25;
		int b_Phy = goliath.phys() * 100;
		
		//Player statistics
		int p_HP = david.hp() * 100;
		int p_Def = david.def() * 25;
		int p_Phy = david.phys() * 100;
		
		Random rand = new Random();
		Scanner input = new Scanner(System.in);
		
		
		//Game keeps running while Goliath's HP does not hit 0
		while(b_HP >= 0 || p_HP >= 0)
		{
			int critChance = rand.nextInt(4)+1;
			int b_RNG = rand.nextInt(10)+1;
			System.out.println("David HP " + p_HP + " " + "Goliath HP " + b_HP);
			System.out.println();
			System.out.println("Enter 1, 2, or 3: 1) Attack, 2) Defend, or 3) Use Potion");
			int userInput = input.nextInt();
			System.out.println();
			
			//User choices
			switch(userInput)
			{
				case 1:
					//Calculates crit, if rolled, and normal damage, low - high roll
					if(critChance == 2)
					{
						damage_dealt = Math.abs(p_Phy - b_Def);
						crit = damage_dealt * 2;
						System.out.println("David hits Goliath's eye for " + crit + " damage.");
						System.out.println();
						b_HP -= crit;
					}
					else
					{
						damage_dealt = Math.abs(p_Phy - b_Def);
						System.out.println("David has dealt " + damage_dealt);
						System.out.println();
						b_HP -= damage_dealt;
					}
					break;
						
				//Calculates damage taken, low - high roll
				case 2:
					damage_taken = b_Phy - p_Def;
					System.out.println("David has taken " + damage_taken);
					System.out.println();
					p_HP -= damage_taken;
					break;
						
				//Enters item choice
				case 3:
					System.out.println("Enter 1 or 2: 1) Attack Potion: x" + item_count_atk + " 2) Defense Potion: x" + item_count_def);
					userInput = input.nextInt();
					System.out.println();
						
					//Choose from one 2 items, 1) attack potion, 2) defense potion
					switch(userInput)
					{
						//Attack Potion
						case 1:
							atkp = true;
							p_HP += 550;
							p_Phy *= 2;
							System.out.println("David has restored 550 HP and gained x2 more Phys for the next 3 turns");
							System.out.println();
								
							//Checks for count on atk potion
							if(item_count_atk != 0)
							{
								item_count_atk--;
								atkp_turns = 4;
							}
							else
							//If the counter is 0, print message that says you run out of potions
							{
									
								System.out.println("You have run out of Attack Potions.");
								System.out.println();
							}
							break;

							
						//Defense Potion
						case 2:	
							defp = true;
							p_HP += 550;
							p_Def *= 5;
							System.out.println("David has restored 550 HP and gained x5 more Def for the next 3 turns");
							System.out.println();
								
							//Checks for count on def potion
							if(item_count_atk != 0)
							{
								item_count_def--;
								defp_turns = 4;
							}
							//If the counter is 0, print message that says you run out of potions
							else
							{
								System.out.println("You have run out of Defense Potions.");
								p_Def /= 5;
								defp = false;
								System.out.println();
							}
							break;
					}
			}
				
				//Boss choices
				//20% chance of connecting
				if(b_RNG == 1 || b_RNG == 2)
				{
					damage_dealt = b_Phy - p_Def;
					crit = damage_dealt * 2;
					System.out.println("The goliath swung his club and hit David for " + crit + " damage");
					System.out.println();
					p_HP -= crit;
				}
				
				//50% chance of connecting
				else if(b_RNG == 3 || b_RNG == 4 || b_RNG == 5 || b_RNG == 6 || b_RNG == 7)
				{
					damage_dealt = b_Phy - p_Def;
					System.out.println("The goliath has dealt " + damage_dealt);
					System.out.println();
					p_HP -= damage_dealt;
				}
				
				//30% chance of missing
				else
				{
					System.out.println("The goliath missed.");
					System.out.println();
				}
				//Lets player know how many turns they have on the potion, revert back to base stats after the potion has expired.
				if(atkp_turns > 0 && atkp == true)
				{
					System.out.println("Turns left for Attack Potion: " + (atkp_turns-1));
					System.out.println();
					atkp_turns--;
					
					if(atkp_turns == 0)
					{
						atkp = false;
						p_Phy /= 2;
						System.out.println("Your Attack Potion has expired.");
						System.out.println();
					}
				}
				
				//Lets player know how many turns they have on the potion, revert back to base stats after the potion has expired.
				if(defp_turns > 0 && defp == true)
				{
					System.out.println("Turns left for Defense Potion: " + (defp_turns-1));
					System.out.println();
					defp_turns--;
					
					if(defp_turns == 0)
					{
						defp = false;
						p_Def /= 5;
						System.out.println("Your Defense Potion has expired.");
						System.out.println();
					}
				}
				//Boss enters Berserk stance, heighting stats
				if((b_HP <= (b_MAXHP/2)) && berStan == false)
				{
					System.out.println("CAUTION!!! THE GOLIATH HAS ENTERED BERSERK STANCE");
					System.out.println();
					b_Def += 20;
					b_Phy += 20;
					berStan = true;
				}
				//Game Over condition and breaks out of loop
				if(b_HP <= 0)
				{
					b_HP = 0;
					System.out.println("David HP " + p_HP + " " + "Goliath HP " + b_HP);
					System.out.println();
					System.out.println("You have slain the Goliath.");
					break;
				}
				else if(p_HP <= 0)
				{
					p_HP = 0;
					System.out.println("David HP " + p_HP + " " + "Goliath HP " + b_HP);
					System.out.println();
					System.out.println("You have been slain by the Goliath.");
					break;
				}	
		}	
	}
}
