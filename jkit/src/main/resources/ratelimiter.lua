local key = KEYS[1] --限流KEY（一秒一个）
local limit = tonumber(ARGV[1]) --限流大小
local time = tonumber(ARGV[2]) --时间窗口大小,单位s

local current = tonumber(redis.call('get', key) or "0")
if(current + 1 > limit) then --如果超出限流大小
    return 0
elseif(current == 0) then
    redis.call("INCRBY", key, "1")
    redis.call("expire", key, time)
    return 1
else
    redis.call("INCRBY", key,"1")
    return 1
end