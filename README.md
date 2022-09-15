<h1>BingoVerifer</h1>
A program that intakes a player's bingo card, a sequence of called numbers, and a pattern card as input and returns whether or not the player has one the game

# Rules of the game 

Sample Input/Outputs

INPUT_FOR_RUN001:
1 0 0 0 0
1 0 0 0 0
1 0 0 0 0
1 0 0 0 0
1 0 0 0 0
09 23 71 01 32 52 06 55 61 69 14 18 19 70 03
01 24 35 56 70
09 23 31 48 75
03 18 00 60 63
06 29 32 58 61
14 30 44 55 64
OUTPUT_FOR_RUN001:
VALID BINGO

INPUT002:
1 0 0 0 0
0 1 0 0 0
0 0 1 0 0
0 0 0 1 0
0 0 0 0 1
09 23 71 01 62 52 60 58 61 69 36 21 12 14 03 18 19 68 70 63 33
01 21 35 56 72
09 23 37 49 74
14 18 00 60 68
06 28 32 58 61
03 30 41 55 64
OUTPUT002:
NO BINGO

INPUT003:
4 4 0 0 0
4 4 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
08 23 58 01 64 52 60 27 61 69 36 21 54 14 03 18 19 68 70 65 33
01 21 35 56 72
09 23 37 49 74
14 18 00 60 68
06 28 32 58 61
03 30 41 54 65
OUTPUT003:
NO BINGO
