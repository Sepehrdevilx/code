class PrimeNumbers
{
   public static void main (String[] args)
   {		
	                  
	                   int number=0;
	                   for(int n=1;n<=1000;n++)
	                   {
	                       int amount=0;
	                       for(int j=2;j<n/2+1;j++)
	                       {
	                           if(n%j==0)
	                           {
	                        	   amount=amount+1;
	                               break;
	                           }
	                       }
	                       if(amount==0 & n!=1)
	                       {
	                           if(n!=997)
	                           {
	                        	   number=number+1;
	                           if(number<=10)
	                               System.out.print(n+",");
	                           else
	                           {
	                               System.out.println();
	                               System.out.print(n+",");
	                               number=1;
	                               }
	                           }
	                           else
	                               System.out.println(n);
	                       }
	                   }
	           }
   }
