T = int(input())
for i in range(T):
    S = int(input())
    n = 0
    for j in range(1, int(S ** 0.5) + 1):
        n += int(S / j) - (j - 1)
    print(n)