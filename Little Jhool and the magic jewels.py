n = int(input())
for x in range(n):
    dic = {}
    p = input()
    jam = "ruby"
    for it in p:
        if it in dic:
            dic[it] += 1
        else:
            if it in jam:
                dic[it] = 1
    maxx = pow(10, 7)
    for it in dic:
        if dic[it] < maxx:
            maxx = dic[it]
    if len(dic) < 4:
        maxx = 0
    print(maxx)