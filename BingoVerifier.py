#Xavier Goudeaux
#Python Bingo Verifier
#10/18/2022


def rotate_90(card):
    n = len(card)
    for row in range(int(n/2)):
        for col in range(row,n - row - 1):
            temp = card[row][col]
            card[row][col] = card[n - 1 - col][row]
            card[n - 1 - col][row] = card[n - 1 - row][n - 1 - col]
            card[n - 1 - row][n - 1 - col] = card[col][n - 1 - row]
            card[col][n - 1 - row] = temp

def pattern_type(pattern):
    for row in pattern:
        if '1' in row:
            return 1
        elif '4' in row:
            return 4
    return -1
        
        		
def win_pattern(pattern, card):
    for row in range(5):
        for col in range(len(pattern[row])):
            if (pattern[row][col] == '1' or pattern[row][col] == '4') and card[row][col][0] != 'X':
                return False
    return True

#last called 
def last_called(pattern, numbers, card):
    last = numbers[-1]
    for row in range(len(card)):
        for col in range(len(card[row])):
            if (card[row][col][0] == 'X' and card[row][col][1:] == last) and (pattern[row][col] == '1' or pattern[row][col] == '4'):
                return True
    return False

#verifies if player has a valid bingo
def verify_bingo(pattern,numbers, card):
    mark_card(numbers, card)
    if pattern_type(pattern) == 1:
        if last_called(pattern, numbers, card) and win_pattern(pattern, card):
            return True
    if pattern_type(pattern) == 4:
        for rotations in range(4):
            if last_called(pattern, numbers, card) and win_pattern(pattern, card):
                return True
            rotate_90(card)
    return False

#marks off called numbers from player card with 'X' symbol
def mark_card(numbers,card):
    for num in numbers:
        for row in range(len(card)):
            for col in range(len(card[row])):
                if card[row][col] == num or card[row][col] == "00":
                    card[row][col] = "X" + card[row][col]

#displays 1d list inputs
def display_1d(numbers):
    for num in numbers:
        print(num, end=' ')
    print()

#displays 2d list inputs
def display_2d(card):
    for row in card:
        for item in row:
            print(item, end=' ')
        print()
    print()

def main():
    print('-'*30)
    print('INPUT:')
    
    inputs = [input() for _ in range(13)]
    input_list = [row for row in inputs if row != '']
    pattern = [input_list[row].split() for row in range(5) ]
    numbers = input_list[5].split()
    card = [input_list[row].split() for row in range(6,len(input_list))]

    print('\nOUTPUT:')
    if(verify_bingo(pattern,numbers, card)):
        print('VALID BINGO\n')
    else:
        print('NO BINGO\n')

if __name__ == "__main__":
    main()