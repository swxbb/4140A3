import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class SunWeixi{
	public static int[][] sbox = {{0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
						  {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
						  {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
						  {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},
						  {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},
						  {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},
						  {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},
						  {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},
						  {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},
						  {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},
						  {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},
						  {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},
						  {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},
						  {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},
						  {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},
						  {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16 }};


	public static int[][] invSbox = {{0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb},
						  {0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb},
						  {0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e},
						  {0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25},
						  {0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92},
						  {0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84},
						  {0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06},
						  {0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b},
						  {0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73},
						  {0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e},
						  {0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b},
						  {0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4},
						  {0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f},
						  {0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef},
						  {0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61},
						  {0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d }};

	public static int[] rCon = {0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36};

	public static int[][] state = new int[4][4];
	public static int[][] key = new int[4][4];
	public static int[] keyExp = new int[176];
	public static int[] invKeyExp = new int[176];

	public static int[][] output = new int[4][4];
	public static int[][] deOuput = new int[4][4];

	public static int rconIndex = 0;

	public static int track = 0;
	public static int detrack = 0;

	public static int invTrack = 0;



	public static void  main(String[] args){

		System.out.println("ENCRYPTION PROCESS");
		System.out.println("-------------------------");
		System.out.println("Plaintext");
		getPlaintText("test1plaintext.txt");
		for(int i = 0; i < 4; i++){
			for(int j = 0; j <4; j++){
				System.out.print(Integer.toHexString(state[i][j]));
			}
		}
		System.out.println();
		System.out.println("Key");
		getKeyText("test1key.txt");
		for(int i = 0; i < 4; i++){
			for(int j = 0; j <4; j++){
				System.out.print(Integer.toHexString(key[i][j]));
			}
		}
		System.out.println();
		System.out.println("Key Schedlue");
		KeyExpansion();
		for(int i = 0;i<keyExp.length;i++){
			if((i%4) == 0 && (i%16) != 0){
				System.out.print(",");
			}
			if((i%16) == 0 && i !=0){
				System.out.println();
			}
			System.out.print(Integer.toHexString(keyExp[i]));
		}
		System.out.println();
		encrypt(state);
		System.out.println("State after call 1 to MixColumns()");
		System.out.println("-------------------------");
		for(int i = 0;i<output.length;i++){
			for(int j = 0;j<output[0].length;j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		System.out.println();
		encrypt(output);
		System.out.println("State after call 2 to MixColumns()");
		System.out.println("-------------------------");
		for(int i = 0;i<output.length;i++){
			for(int j = 0;j<output[0].length;j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		System.out.println();
		encrypt(output);
		System.out.println("State after call 3 to MixColumns()");
		System.out.println("-------------------------");
		for(int i = 0;i<output.length;i++){
			for(int j = 0;j<output[0].length;j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		System.out.println();
		encrypt(output);
		System.out.println("State after call 4 to MixColumns()");
		System.out.println("-------------------------");
		for(int i = 0;i<output.length;i++){
			for(int j = 0;j<output[0].length;j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		System.out.println();
		encrypt(output);
		System.out.println("State after call 5 to MixColumns()");
		System.out.println("-------------------------");
		for(int i = 0;i<output.length;i++){
			for(int j = 0;j<output[0].length;j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		System.out.println();
		encrypt(output);
		System.out.println("State after call 6 to MixColumns()");
		System.out.println("-------------------------");
		for(int i = 0;i<output.length;i++){
			for(int j = 0;j<output[0].length;j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		System.out.println();
		encrypt(output);
		System.out.println("State after call 7 to MixColumns()");
		System.out.println("-------------------------");
		for(int i = 0;i<output.length;i++){
			for(int j = 0;j<output[0].length;j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		System.out.println();
		encrypt(output);
		System.out.println("State after call 8 to MixColumns()");
		System.out.println("-------------------------");
		for(int i = 0;i<output.length;i++){
			for(int j = 0;j<output[0].length;j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		System.out.println();
		encrypt(output);
		System.out.println("State after call 9 to MixColumns()");
		System.out.println("-------------------------");
		for(int i = 0;i<output.length;i++){
			for(int j = 0;j<output[0].length;j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		System.out.println();
		cipher(output);
		System.out.println("CipherText: ");
		for(int i = 0;i<output.length;i++){
			for(int j = 0;j<output[0].length;j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		System.out.println();
		System.out.println();


		System.out.println("ENCRYPTION PROCESS");
		System.out.println("-------------------------");
		System.out.println("CipherText: ");
		for(int i = 0; i < 4; i++){
			for(int j = 0; j <4; j++){
				System.out.print(Integer.toHexString(output[i][j]) + " ");
			}
		}
		System.out.println();
		invKeyExpansion();
		firstDecrypt(output);
		System.out.println();
		
		System.out.println("State after call 1 to InvMixColumns()");
		System.out.println("-------------------------");
		decrypt(deOuput);
		System.out.println();
		System.out.println();
		
		System.out.println("... 2 to 9...");
		System.out.println("-------------------------");
		System.out.println("End of processing...");
		System.out.println();

	}


	

	public static void firstDecrypt(int[][] curr){
		for(int m = 0;m<curr.length;m++){
			for(int n = 0;n<curr[0].length;n++){
				deOuput[m][n] = curr[m][n] ^ invKeyExp[detrack];
				detrack++;
			}
		}

		deOuput = InvSubBytes(deOuput);
		deOuput = InvShiftRows(deOuput);

		
	}

	public static void decrypt(int[][] curr){
		for(int m = 0;m<curr.length;m++){
			for(int n = 0;n<curr[0].length;n++){
				deOuput[m][n] = curr[m][n] ^ invKeyExp[detrack];
				detrack++;
			}
		}

		deOuput = InvMixColumns(deOuput);
		printInv(deOuput);
		
		deOuput = InvSubBytes(deOuput);
		deOuput = InvShiftRows(deOuput);
		

	}

	public static void printInv(int[][] curr){
		for(int i = 0;i<curr.length;i++){
			for(int j = 0;j<curr[0].length;j++){
				System.out.print(Integer.toHexString(curr[i][j]) + " ");
			}
		}
	}

	public static void encrypt(int[][] curr){
		for(int m = 0;m<curr.length;m++){
			for(int n = 0;n<curr[0].length;n++){
				output[m][n] = curr[m][n] ^ keyExp[track];
				track++;
			}
		}
		
		output = SubBytes(output);
		output = ShiftRows(output);
		output = MixColumns(output);
		
	}


	public static void invKeyExpansion(){
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-16];
			invTrack++;
		}
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-32];
			invTrack++;
		}
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-48];
			invTrack++;
		}
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-64];
			invTrack++;
		}
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-80];
			invTrack++;
		}
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-96];
			invTrack++;
		}
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-112];
			invTrack++;
		}
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-128];
			invTrack++;
		}
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-144];
			invTrack++;
		}
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-160];
			invTrack++;
		}
		for(int i = 0;i<16;i++){
			invKeyExp[invTrack] = keyExp[keyExp.length+i-176];
			invTrack++;
		}


	}


	public static void KeyExpansion(){
		int[] temp = new int[4];

		int keyExpIndex = 0;
		int tempIndex = 0;
		int boxVal = 0;

		//put index 0-4 into keyExpansion.
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				keyExp[keyExpIndex] = key[i][j];
				keyExpIndex++;
			}
		}
		
		//put the rest of the key.
		for(int i = 16; i < keyExp.length;i = i + 4){
			if((i%16) == 0){
				temp[0] = keyExp[i-4];
				temp[1] = keyExp[i-3];
				temp[2] = keyExp[i-2];
				temp[3] = keyExp[i-1];
				
				temp = firstToLast(temp);
				
				for(int j = 0; j<4;j++){
					temp[j] = checkInBox(temp[j],sbox);
				}


				temp[0] = temp[0] ^ rCon[rconIndex];

				rconIndex++;

				keyExp[i] = keyExp[i-16] ^ temp[0];
				keyExp[i+1] = keyExp[i-15] ^ temp[1];
				keyExp[i+2] = keyExp[i-14] ^ temp[2];
				keyExp[i+3] = keyExp[i-13] ^ temp[3];	

				
				
				tempIndex = 0;
			}else{
				keyExp[i] = keyExp[i-16] ^ keyExp[i-4];
				keyExp[i+1] = keyExp[i-15] ^ keyExp[i-3];
				keyExp[i+2] = keyExp[i-14] ^ keyExp[i-2];
				keyExp[i+3] = keyExp[i-13] ^ keyExp[i-1];
			
			}

		}
		
	}

	public static int checkInBox(int curr, int[][] box){
		int row = (curr >> 4) & 0x0f;
		int col = curr & 0x0f;
		int result = 0;

		result = box[row][col];

		return result;
	}

	public static int[] firstToLast(int[] curr){
		int[] result = new int[curr.length];

		result[0] = curr[1];
		result[1] = curr[2];
		result[2] = curr[3];
		result[3] = curr[0];
		return result;

	}

	public static void getPlaintText(String fileName){
		String line = null;
		String spLine[] = null;

		

		FileReader fr = null;
		BufferedReader br = null;

		try{
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			int index = 0;

				line = br.readLine();
				spLine = line.split(" ");
				for(int i = 0; i < 4; i++){
					for(int j = 0; j < 4; j++){
						state[i][j] = Integer.parseInt(spLine[index],16);
						index++;
					}
				}
		
		}catch (IOException e){
			e.printStackTrace();
		}

	}
	public static void getKeyText(String fileName){
		String line = null;
		String spLine[] = null;

		

		FileReader fr = null;
		BufferedReader br = null;

		try{
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			int index = 0;

				line = br.readLine();
				spLine = line.split(" ");
				for(int i = 0; i < 4; i++){
					for(int j = 0; j < 4; j++){
						key[i][j] = Integer.parseInt(spLine[index],16);
						index++;
					}
				}
		
		}catch (IOException e){
			e.printStackTrace();
		}

	}

	public static int[][] SubBytes(int[][] curr){
		int[][] result = new int [4][4];

		int row = 0;
		int col = 0;

		for(int i = 0; i < 4;i++){
			for(int j = 0; j< 4; j++){
				row = (curr[i][j] >> 4) & 0x0f;
				col = curr[i][j] & 0x0f;

				result[i][j] = sbox[row][col];
			}
		}
		return result;
	}

	public static int[][] InvSubBytes(int[][] curr){
		int[][] result = new int [4][4];

		int row = 0;
		int col = 0;

		for(int i = 0; i < 4;i++){
			for(int j = 0; j< 4; j++){
				row = (curr[i][j] >> 4) & 0x0f;
				col = curr[i][j] & 0x0f;

				result[i][j] = invSbox[row][col];
			}
		}
		return result;
	}






	public static int[][] ShiftRows(int[][] curr){
		int[][] result = new int[4][4];

		result[0][0] = curr[0][0];
		result[0][1] = curr[1][1];
		result[0][2] = curr[2][2];
		result[0][3] = curr[3][3];

		result[1][0] = curr[1][0];
		result[1][1] = curr[2][1];
		result[1][2] = curr[3][2];
		result[1][3] = curr[0][3];

		result[2][0] = curr[2][0];
		result[2][1] = curr[3][1];
		result[2][2] = curr[0][2];
		result[2][3] = curr[1][3];

		result[3][0] = curr[3][0];
		result[3][1] = curr[0][1];
		result[3][2] = curr[1][2];
		result[3][3] = curr[2][3];

		
	

		return result;
	}

	public static int[][] InvShiftRows(int[][] curr){
		int[][] result = new int[4][4];

		result[0][0] = curr[0][0];
		result[0][1] = curr[3][1];
		result[0][2] = curr[2][2];
		result[0][3] = curr[1][3];

		result[1][0] = curr[1][0];
		result[1][1] = curr[0][1];
		result[1][2] = curr[3][2];
		result[1][3] = curr[2][3];

		result[2][0] = curr[2][0];
		result[2][1] = curr[1][1];
		result[2][2] = curr[0][3];
		result[2][3] = curr[3][1];

		result[3][0] = curr[3][0];
		result[3][1] = curr[2][1];
		result[3][2] = curr[1][2];
		result[3][3] = curr[0][3];

		return result;
	}

	public static int doubleVal(int curr){
		int result = curr << 1; 
		int lastDig = result & 0x00000100;
		if(lastDig != 0){
			result = result & 0x000000ff;
			result = result ^ 0x1b;
		}
		return result;
	}

	public static int tripleVal(int curr){;
		return doubleVal(curr) ^ curr;
	}

	public static int[][] MixColumns(int[][] curr){
		int[][] result = new int[4][4];

		result[0][0] = doubleVal(curr[0][0]) ^ tripleVal(curr[0][1]) ^ curr[0][2] ^ curr[0][3];
		result[0][1] = curr[0][0] ^ doubleVal(curr[0][1]) ^ tripleVal(curr[0][2]) ^ curr[0][3];
		result[0][2] = curr[0][0] ^ curr[0][1] ^ doubleVal(curr[0][2]) ^ tripleVal(curr[0][3]);
		result[0][3] = tripleVal(curr[0][0]) ^ curr[0][1] ^ curr[0][2] ^ doubleVal(curr[0][3]);

		result[1][0] = doubleVal(curr[1][0]) ^ tripleVal(curr[1][1]) ^ curr[1][2] ^ curr[1][3];
		result[1][1] = curr[1][0] ^ doubleVal(curr[1][1]) ^ tripleVal(curr[1][2]) ^ curr[1][3];
		result[1][2] = curr[1][0] ^ curr[1][1] ^ doubleVal(curr[1][2]) ^ tripleVal(curr[1][3]);
		result[1][3] = tripleVal(curr[1][0]) ^ curr[1][1] ^ curr[1][2] ^ doubleVal(curr[1][3]);

		result[2][0] = doubleVal(curr[2][0]) ^ tripleVal(curr[2][1]) ^ curr[2][2] ^ curr[2][3];
		result[2][1] = curr[2][0] ^ doubleVal(curr[2][1]) ^ tripleVal(curr[2][2]) ^ curr[2][3];
		result[2][2] = curr[2][0] ^ curr[2][1] ^ doubleVal(curr[2][2]) ^ tripleVal(curr[2][3]);
		result[2][3] = tripleVal(curr[2][0]) ^ curr[2][1] ^ curr[2][2] ^ doubleVal(curr[2][3]);

		result[3][0] = doubleVal(curr[3][0]) ^ tripleVal(curr[3][1]) ^ curr[3][2] ^ curr[3][3];
		result[3][1] = curr[3][0] ^ doubleVal(curr[3][1]) ^ tripleVal(curr[3][2]) ^ curr[3][3];
		result[3][2] = curr[3][0] ^ curr[3][1] ^ doubleVal(curr[3][2]) ^ tripleVal(curr[3][3]);
		result[3][3] = tripleVal(curr[3][0]) ^ curr[3][1] ^ curr[3][2] ^ doubleVal(curr[3][3]);


		return result;
	}

	public static int[][] InvMixColumns(int[][] curr){
		int[][] result = new int[4][4];

		result[0][0] = eVal(curr[0][0]) ^ bVal(curr[0][1]) ^ dVal(curr[0][2]) ^ nineVal(curr[0][3]);
		result[0][1] = nineVal(curr[0][0]) ^ eVal(curr[0][1]) ^ bVal(curr[0][2]) ^ dVal(curr[0][3]);
		result[0][2] = dVal(curr[0][0]) ^ nineVal(curr[0][1]) ^ eVal(curr[0][2]) ^ bVal(curr[0][3]);
		result[0][3] = bVal(curr[0][0]) ^ dVal(curr[0][1]) ^ nineVal(curr[0][2]) ^ eVal(curr[0][3]);

		result[1][0] = eVal(curr[1][0]) ^ bVal(curr[1][1]) ^ dVal(curr[1][2]) ^ nineVal(curr[1][3]);
		result[1][1] = nineVal(curr[1][0]) ^ eVal(curr[1][1]) ^ bVal(curr[1][2]) ^ dVal(curr[1][3]);
		result[1][2] = dVal(curr[1][0]) ^ nineVal(curr[1][1]) ^ eVal(curr[1][2]) ^ bVal(curr[1][3]);
		result[1][3] = bVal(curr[1][0]) ^ dVal(curr[1][1]) ^ nineVal(curr[1][2]) ^ eVal(curr[1][3]);

		result[2][0] = eVal(curr[2][0]) ^ bVal(curr[2][1]) ^ dVal(curr[2][2]) ^ nineVal(curr[2][3]);
		result[2][1] = nineVal(curr[2][0]) ^ eVal(curr[2][1]) ^ bVal(curr[2][2]) ^ dVal(curr[2][3]);
		result[2][2] = dVal(curr[2][0]) ^ nineVal(curr[2][1]) ^ eVal(curr[2][2]) ^ bVal(curr[2][3]);
		result[2][3] = bVal(curr[2][0]) ^ dVal(curr[2][1]) ^ nineVal(curr[2][2]) ^ eVal(curr[2][3]);

		result[3][0] = eVal(curr[3][0]) ^ bVal(curr[3][1]) ^ dVal(curr[3][2]) ^ nineVal(curr[3][3]);
		result[3][1] = nineVal(curr[3][0]) ^ eVal(curr[3][1]) ^ bVal(curr[3][2]) ^ dVal(curr[3][3]);
		result[3][2] = dVal(curr[3][0]) ^ nineVal(curr[3][1]) ^ eVal(curr[3][2]) ^ bVal(curr[3][3]);
		result[3][3] = bVal(curr[3][0]) ^ dVal(curr[3][1]) ^ nineVal(curr[3][2]) ^ eVal(curr[3][3]);

		return result;
	}
	public static int nineVal(int curr){
		return doubleVal(doubleVal(doubleVal(curr))) ^ curr;
	}
	public static int bVal(int curr){
		return nineVal(curr) ^ doubleVal(curr);
	}
	public static int dVal(int curr){
		return nineVal(curr)^doubleVal(doubleVal(curr));
	}
	public static int eightVal(int curr){
		return doubleVal(doubleVal(doubleVal(curr)));
	}
	public static int eVal(int curr){
		return eightVal(curr) ^ doubleVal(tripleVal(curr));
	}

	

	public static void cipher(int[][] curr){
		for(int m = 0;m<curr.length;m++){
			for(int n = 0;n<curr[0].length;n++){
				output[m][n] = curr[m][n] ^ keyExp[track];
				track++;
			}
		}
		output = SubBytes(output);
		output = ShiftRows(output); 
		for(int m = 0;m<output.length;m++){
			for(int n = 0;n<output[0].length;n++){
				output[m][n] = output[m][n] ^ keyExp[track];
				track++;
			}
		}

	}
	






















}