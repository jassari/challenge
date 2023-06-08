from csv import reader

INPUT_FILE = 'input.csv'
OUTPUT_FILE = 'result.txt'

opened_file = open(INPUT_FILE)
read_file = reader(opened_file)
data = list(read_file)

# 1. Extracting target sum
all_numbers = data[0]
last_numbers = all_numbers[-1].split(" ")
first_last_number = last_numbers[0]
targetSum = int(last_numbers[1])

# 2. Removing targetSum from list
all_numbers[-1] = first_last_number

# 3. Transforming string numbers to integers
all_numbers_integers = [eval(i) for i in all_numbers]

# 4. Getting pairs
def pairSum(all_numbers_integers, targetSum):
    pairs = {}
    for number in all_numbers_integers:
        number2 = targetSum - number
        if number2 not in pairs:
            if number2 != 0 and number2 in  all_numbers_integers:
                pairs[number] = number2
    return pairs

pairs = pairSum(all_numbers_integers, targetSum)

# 5. Writing results to file
pairs_string = ''
for key in pairs:
    pairs_string += f'+ ' + str(key) + ',' + str(pairs[key]) + '\n'

result_file = open(OUTPUT_FILE, 'w+')
result_file.write(pairs_string)

opened_file.close()
result_file.close()







