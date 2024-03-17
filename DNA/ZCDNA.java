// Programmer: Zach Carlson
// Class: CS &141
// Date: Due 8/8/2023
// Assignment: Lab 6 - DNA
// Purpose: A program to review input file information and the output information from that file to console and then to output file

import java.util.*; // class import for java Util
import java.io.*; // class import for input/output

public class ZCDNA {

   // variable declaration

   public static final double massA = 135.128; // mass of Adenine (A)
   public static final double massC = 111.103; // mass of Cytosine (C)
   public static final double massG = 151.128; // mass of Guanine (G)
   public static final double massT = 125.107; // mass of Thymine (T)
   public static final double massJunk = 100.000; // assumed mass of junk (-) 
   public static final int nucleotidesPerCodon = 3; // codon groups will always be 3 nucleotides

   public static void main(String[] args) throws FileNotFoundException {

      Scanner input = new Scanner(System.in); // Create a Scanner to obtain input from the command window
   
      intro();
      space();
   
      // input file query
   
      String fileName = getFileName(input);
      System.out.println("You entered: " + fileName);
      space();
   
      // file processing and output to console
      
      processDNAFile(fileName);
   
      // file output query at the end of the processDNAFile 
   
   } // end of main method, very short
   
   public static void space() { // always need space
      System.out.println();
   } // end of space/new line method
   
   public static void intro() { // introducing the program
      System.out.println("Hello!");
      System.out.println("This program reports information about DNA");
      System.out.println("nucleotide sequences that may encode proteins.");
   } // end of intro method
   
   public static String getFileName(Scanner scanner) { // method to query user to input the txt file to reference
      System.out.print("Please enter the name of the .txt file: ");
      String fileName = scanner.nextLine();
      return fileName;
   } // end of getFileName method
   
   public static String getOutputFileName(Scanner input) { // method to query user to specify the output file name
      System.out.print("Output to file? (y/n): ");
      String response = input.next().toLowerCase();
      if (response.equals("y")) {
         System.out.print("Enter the desired file name: ");
         return input.next();
      }
      return null; // returning null indicates and catches the user doesn't want to output to a file
   } // end of getOutputFileName method
   
   public static String[] processDNAFile(String fileName) { // where all the magic happens
      StringBuilder output = new StringBuilder(); // to store all created content to "output"
      String[] sequences = new String[numberOfSequences(fileName)]; 
      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { // using tool BufferedReader to store information as variable "br"
         String title;
         int index = 0;
         while ((title = br.readLine()) != null) { // while Region Name line is not empty or "null"
            String sequence = br.readLine();
            if (sequence == null) { // error catch for empty or "null" Region Name
               System.out.println("No DNA sequence found for Region Name: " + title);
               break;
            }
                
            sequence = sequence.toUpperCase(); // forcing all chars to uppercase
            sequences[index++] = sequence;
         
            // Print the nucleotide counts for the current sequence right after the sequence
            // using output.append to add content to the output file
            
            int[] counts = countNucleotides(sequence);
            double[] results = calculateTotalMassAndPercentage(counts);
            output.append("Region Name: " + title + "\n");
            output.append("Nucleotides: " + sequence + "\n");
            output.append("Nuc. Counts: A: " + counts[0] + ", C: " + counts[1] + ", G: " + counts[2] + ", T: " + counts[3] + ", Junk: " + counts[4] + "\n");
            output.append(String.format("Total Mass%%: [%.1f, %.1f, %.1f, %.1f] of %.1f%n", results[0], results[1], results[2], results[3], results[4])); 
                   
            // Printing Codons
            
            output.append("Codons List: [");
            String[] codonsArray = getArrayOfCodons(sequence);
            String[] codonsWithDuplicates = processCodonDuplicates(codonsArray);
            for (int i = 0; i < codonsWithDuplicates.length; i++) {
               if (i > 0) {
                  output.append(", ");
               }
               output.append(codonsWithDuplicates[i]);
            }
            output.append("]\n");
            
            // Protein Check
            
            if (isProtein(sequence)) {
               output.append("Is Protein?: Yes\n\n"); // last line, two new lines to create a space for the next sequence data group
            } 
            else {
               output.append("Is Protein?: No\n\n"); // last line, two new lines to create a space for the next sequence data group
            }
         }
         
         output.append("\n"); // adding a new line to space sequenced nucelotide strings
         
         System.out.print(output.toString()); // outputting processed sequences to console
         
         Scanner input = new Scanner(System.in);
         String outputFile = getOutputFileName(input);
         if (outputFile != null) { // do if output file name is not empty or "null"
            saveToFile(output.toString(), outputFile); 
         } 
      } 
      catch (IOException e) { // error catch
         System.out.println("Error processing the file: " + e.getMessage());
      }
      return sequences;
   } // end of processDNAFile
   
   public static int[] countNucleotides(String sequence) { // method enumerates and stores values of nucleotides counts in an array
      int[] counts = new int[5];  // A, C, G, T, junk array
      for (char c : sequence.toCharArray()) {
         switch (c) { // using a switch case to tally each nucleotide and anything else to junk
            case 'A':
               counts[0]++;
               break;
            case 'C':
               counts[1]++;
               break;
            case 'G':
               counts[2]++;
               break;
            case 'T':
               counts[3]++;
               break;
            default:
               counts[4]++;
         }
      }
      return counts;
   } // end of countNucleotides method
   
   public static int numberOfSequences(String fileName) { // method determines the number of nucleotide groups to sequence
      int count = 0; // starting at line 0
      try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { // using tool BufferedReader to store information as variable "br"
         while (br.readLine() != null) { // while line is not empty or "null"
            br.readLine();  // Skips the sequence line
            count++; //incrementing the count to next line
         }
      } 
      catch (IOException e) { // error catch
         System.out.println("Error processing the file: " + e.getMessage());
      }
      return count;
   } // end of numberOfSequences
   
   public static double[] calculateTotalMassAndPercentage(int[] counts) {
      // Calculate the total mass using counts and the predefined constants for each nucleotide mass
      double totalMass =   counts[0] * massA + 
                           counts[1] * massC + 
                           counts[2] * massG + 
                           counts[3] * massT + 
                           counts[4] * massJunk;
      
      // Calculates the mass percentage for each nucleotide and stores them in an array
      double[] percentages = {
         (counts[0] * massA) / totalMass * 100,
         (counts[1] * massC) / totalMass * 100,
         (counts[2] * massG) / totalMass * 100,
         (counts[3] * massT) / totalMass * 100
         };
      
      // Return an array that includes the mass percentages and the total mass
      return new double[]{percentages[0], percentages[1], percentages[2], percentages[3], totalMass};
   } // end of calculateTotalMassAndPercentage method
   
   public static String[] getArrayOfCodons(String sequence) {
      // Calculate the number of codons by dividing the length of the sequence by the number of nucleotides per codon
      int numCodons = sequence.length() / nucleotidesPerCodon;
      
      // Initialize an array to store the codons with the length equal to the number of codons
      String[] codons = new String[numCodons];
      
      // Apply through the sequence, extracting each codon (3 nucleotides) and storing it in the codons array
      for (int i = 0; i < numCodons; i++) {
         // Using substring to extract each codon, starting from the current index multiplied by the number of nucleotides per codon
         // and ending 3 characters later
         codons[i] = sequence.substring(i * nucleotidesPerCodon, i * nucleotidesPerCodon + nucleotidesPerCodon);
      }
   
      return codons;
   } // end of getArrayOfCodons method
   
   public static String[] processCodonDuplicates(String[] codons) { // method just to combine duplicate codon groups and add a number to that group for each time it appears in the sequence, decreasing print size
      String[] processed = new String[codons.length]; // creating the string and naming it "processed" and then same length as the input codons
      
      // Apply through each codon in the input array
      for (int i = 0; i < codons.length; i++) {
         // Initialize a count for the current codon
         int count = 1;
         
         // Apply through all the other codons to compare with the current one
         for (int j = 0; j < codons.length; j++) {
            if (i != j && codons[i].equals(codons[j])) {
               // Increment the count for the current codon
               count++;
            }
         }
         // If the count is greater than 1, append the count to the codon, otherwise keep it as is
         processed[i] = count > 1 ? codons[i] + "(" + count + ")" : codons[i];
      }
      
      // Return the processed codons array, with duplicates marked with counts
      return processed;
   } // end of processCodonDuplicates method
   
   public static boolean isProtein(String sequence) { // using true/false flag and variable checks to determine if a sequence is a protein
      final int minCodons = 5; // declaring number of codos needed for protein is 5
      final String startCodon = "ATG"; // declaring start codon must be ATG
      final String[] endCodons = {"TAA", "TAG", "TGA"}; // declaring end codon must be TAA, TAG or TGA
      final double minMassPercentage = 30.0; // declaring total mass of C and G in the given sequence must be greater than 30.0%
   
    // Calculate codons and check number of codons
      String[] codonsArray = getArrayOfCodons(sequence);
      if (codonsArray.length < minCodons) {
         return false;
      }
   
    // Check start codon
      if (!codonsArray[0].equals(startCodon)) { // if the codon in position 0 of the codonsArray is not ATG, return false
         return false;
      }
   
    // Check end codons
      boolean hasValidEndCodon = false;
      for (String endCodon : endCodons) { // creates a string for all three valid end Codons
         if (codonsArray[codonsArray.length - 1].equals(endCodon)) { // compares the end codons and flips the flag is a match is found
            hasValidEndCodon = true;
            break;
         }
      }
      if (!hasValidEndCodon) { // if hasValidEndCodon does not match, return false
         return false;
      }
   
    // Check mass percentage for C and G combined
      int[] counts = countNucleotides(sequence);
      double totalMass = counts[0] * massA + 
                       counts[1] * massC + 
                       counts[2] * massG + 
                       counts[3] * massT + 
                       counts[4] * massJunk;
      double cgCombinedMass = (counts[1] * massC) + (counts[2] * massG); 
      double cgCombinedPercentage = (cgCombinedMass / totalMass) * 100;
   
      if (cgCombinedPercentage < minMassPercentage) {
         return false;
      }
   
    // If all checks pass
      return true;
   } // end of isProtein method
   
   public static void saveToFile(String output, String filename) { // method to write the created string output to file
      try (PrintStream ps = new PrintStream(new FileOutputStream(filename))) { // connecting the action, object and file name
         ps.print(output); // printing the output to file
      } catch (IOException e) { // error catch
         System.out.println("Error writing to file: " + e.getMessage());
      }
   } // end of saveToFile method
   
} // end of class