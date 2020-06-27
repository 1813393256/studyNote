# Redis学习

## Redis数据类型

> String:字符串
>
> >set name "笔记"
> >
> >get name
>
> Hash：散列(特别适合存储对象)
>
> >del name:删除前面用过的key
> >
> >hmset name field1"hello" field2 "world"
> >
> >hget name field1
>
> List:列表(有序)
>
> > del name
> >
> > lpush name redis
> >
> > lpush name mysql
> >
> > lrange name 0 10
>
> Set:集合(无序,不重复)：String类型的无序集合，通过hash表实现，复杂度为O(1) : sadd key member
>
> > del name
> >
> > sadd name redis
> >
> > sadd name mysql
> >
> > smembers name
>
> Zset(Sorted Set)：有序集合， 将Set中的元素增加一个权重参数score,元素按score有序排列 
>
> > zadd key score member
> >
> > del name 
> >
> > zadd name 0 redis
> >
> > zadd name 0 mysql
> >
> > zrangescore name 0 1000

## Redis事务

> discard:取消事务
>
> exec：执行所有事务块内的命令
>
> multi：标记一个事务块的开始
>
> unwatch：取消watch命令对所有key的监视
>
> watch key[key...]：监视一个(或多个)key，如果在事务执行前(或这些)key被其他命令所改动，那么事务将被打断

