/**
 * 定义Map
 * */
function Map() {
    this.container = {};
}

/**
 * Map的put方法
 * */
Map.prototype.put = function (key, value) {
    try {
        if (key != null && key != "") {
            this.container[key] = value;
        }
    } catch (e) {
        return e;
    }
};

/**
 * Map的根据key值get出value方法
 * */
Map.prototype.get = function (key) {
    try {
        return this.container[key];
    } catch (e) {
        return e;
    }
};

/**
 * Map的containsKey方法
 * */
Map.prototype.containsKey = function (key) {
    try {
        for (var p in this.container) {
            if (p == key) {
                return true;
            }
        }
        return false;
    } catch (e) {
        return e;
    }
}

/**
 * Map的containsValue方法
 * */
Map.prototype.containsValue = function (value) {
    try {
        for (var p in this.container) {
            if (this.container[p] === value) {
                return true;
            }
        }
        return false;
    } catch (e) {
        return e;
    }
};


/**
 * Map的删除元素方法
 * */
Map.prototype.remove = function (key) {
    try {
        delete this.container[key];
    } catch (e) {
        return e;
    }
};

/**
 * 清除Map的方法
 * */
Map.prototype.clear = function () {
    try {
        delete this.container;
        this.container = {};
    } catch (e) {
        return e;
    }
};

/**
 * 判断map是否为空
 * */
Map.prototype.isEmpty = function () {
    if (this.keyArray().length == 0)
        return true;
    else
        return false;
};

/**
 * 获取map的大小
 * */
Map.prototype.size = function () {
    return this.keyArray().length;
}

/**
 * 返回Map的所有Key值数组
 * */
Map.prototype.keyArray = function () {
    var keys = new Array();
    for (var p in this.container) {
        keys.push(p);
    }
    return keys;
}

/**
 * 返回map中的value值数组
 * */
Map.prototype.valueArray = function () {
    var values = new Array();
    var keys = this.keyArray();
    for (var i = 0; i < keys.length; i++) {
        values.push(this.container[keys[i]]);
    }
    return values;
}
