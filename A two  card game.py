n = int(input())
c = []
while len(c) < n:
    c += list(map(int, input().split()))
c.sort()
a = 0
i = -1
for j in range(n):
    while i + 1 < j and c[i + 1] < c[j]:
        i += 1
    a += i + 1
b = 0
i = n
for j in range(n - 1, -1, -1):
    while i - 1 > j and c[i - 1] > c[j]:
        i -= 1
    b += n - i
print('%.6f %.6f' % (a / n / (n - 1.0), b / n / (n - 1.0)))