ans = 0
for _ in range(int(input())):
    a, b, c = map(int, input().split())
    if b * b >= 4 * a * c:
        ans += 1
print(ans)