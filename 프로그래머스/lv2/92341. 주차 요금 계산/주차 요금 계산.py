def solution(fees, records):
    ans = []

    total_time = dict()
    record_time = dict()

    for record in records:
        rec = record.split(' ')
        curr_time = convert_time(rec[0])
        car_num = rec[1]
        recorded_time = record_time.get(car_num)
        if recorded_time is not None:
            diff = curr_time - recorded_time
            total_time_num = total_time.get(car_num)
            if total_time_num is not None:
                total_time[car_num] += diff
            else:
                total_time[car_num] = diff
            record_time.pop(car_num)
        else:
            record_time[car_num] = curr_time

    last_time = convert_time("23:59")
    for left_car_num in record_time:
        diff = last_time - record_time[left_car_num]
        total_time_num = total_time.get(left_car_num)
        if total_time_num is not None:
            total_time[left_car_num] += diff
        else:
            total_time[left_car_num] = diff

    keys = sorted(total_time.keys())
    for key in keys:
        time = total_time[key]
        if time <= fees[0]:
            ans.append(fees[1])
        else:
            total_over_time = time - fees[0]
            unit_over_fee = (total_over_time // fees[2]) if total_over_time % fees[2] == 0 else (total_over_time // fees[2] + 1)
            ans.append(fees[1] + unit_over_fee * fees[3])
            
    return ans

def convert_time(string):
    tmp = string.split(':')
    return int(tmp[0]) * 60 + int(tmp[1])