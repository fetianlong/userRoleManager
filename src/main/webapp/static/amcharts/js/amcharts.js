var inheriting = {
},
AmCharts = {
  Class: function (a) {
    var b = function () {
      arguments[0] !== inheriting && (this.events = {
      }, this.construct.apply(this, arguments))
    };
    a.inherits ? (b.prototype = new a.inherits(inheriting), b.base = a.inherits.prototype, delete a.inherits)  : (b.prototype.createEvents = function () {
      for (var a = 0, b = arguments.length; a < b; a++) this.events[arguments[a]] = [
      ]
    }, b.prototype.listenTo = function (a, b, c) {
      a.events[b].push({
        handler: c,
        scope: this
      })
    }, b.prototype.addListener = function (a, b, c) {
      this.events[a].push({
        handler: b,
        scope: c
      })
    }, b.prototype.removeListener = function (a, b, c) {
      a = a.events[b];
      for (b = a.length - 1; 0 <= b; b--) a[b].handler === c && a.splice(b, 1)
    }, b.prototype.fire = function (a, b) {
      for (var c = this.events[a], g = 0, h = c.length; g < h; g++) {
        var j = c[g];
        j.handler.call(j.scope, b)
      }
    });
    for (var c in a) b.prototype[c] = a[c];
    return b
  },
  charts: [
  ]
};
AmCharts.addChart = function (a) {
  AmCharts.charts.push(a)
};
AmCharts.removeChart = function (a) {
  for (var b = AmCharts.charts, c = b.length - 1; 0 <= c; c--) b[c] == a && b.splice(c, 1)
};
document.addEventListener && (AmCharts.isNN = !0, AmCharts.isIE = !1, AmCharts.ddd = 0.5);
document.attachEvent && (AmCharts.isNN = !1, AmCharts.isIE = !0, AmCharts.ddd = 0);
AmCharts.IEversion = 0;
- 1 != navigator.appVersion.indexOf('MSIE') && document.documentMode && (AmCharts.IEversion = document.documentMode);
9 <= AmCharts.IEversion && (AmCharts.ddd = 0.5);
AmCharts.handleResize = function () {
  for (var a = AmCharts.charts, b = 0; b < a.length; b++) {
    var c = a[b];
    c && c.div && c.handleResize()
  }
};
AmCharts.handleMouseUp = function (a) {
  for (var b = AmCharts.charts, c = 0; c < b.length; c++) {
    var d = b[c];
    d && d.handleReleaseOutside(a)
  }
};
AmCharts.handleMouseMove = function (a) {
  for (var b = AmCharts.charts, c = 0; c < b.length; c++) {
    var d = b[c];
    d && d.handleMouseMove(a)
  }
};
AmCharts.resetMouseOver = function () {
  for (var a = AmCharts.charts, b = 0; b < a.length; b++) {
    var c = a[b];
    c && (c.mouseIsOver = !1)
  }
};
AmCharts.onReadyArray = [
];
AmCharts.ready = function (a) {
  AmCharts.onReadyArray.push(a)
};
AmCharts.handleLoad = function () {
  for (var a = AmCharts.onReadyArray, b = 0; b < a.length; b++) (0, a[b]) ()
};
AmCharts.isNN && (document.addEventListener('mousemove', AmCharts.handleMouseMove, !0), window.addEventListener('resize', AmCharts.handleResize, !0), document.addEventListener('mouseup', AmCharts.handleMouseUp, !0), window.addEventListener('load', AmCharts.handleLoad, !0));
AmCharts.isIE && (document.attachEvent('onmousemove', AmCharts.handleMouseMove), window.attachEvent('onresize', AmCharts.handleResize), document.attachEvent('onmouseup', AmCharts.handleMouseUp), window.attachEvent('onload', AmCharts.handleLoad));
AmCharts.AmChart = AmCharts.Class({
  construct: function () {
    this.version = '2.5.3';
    AmCharts.addChart(this);
    this.createEvents('dataUpdated');
    this.height = this.width = '100%';
    this.dataChanged = !0;
    this.chartCreated = !1;
    this.previousWidth = this.previousHeight = 0;
    this.backgroundColor = '#FFFFFF';
    this.borderAlpha = this.backgroundAlpha = 0;
    this.color = this.borderColor = '#000000';
    this.fontFamily = 'Verdana';
    this.fontSize = 11;
    this.numberFormatter = {
      precision: - 1,
      decimalSeparator: '.',
      thousandsSeparator: ','
    };
    this.percentFormatter = {
      precision: 2,
      decimalSeparator: '.',
      thousandsSeparator: ','
    };
    this.labels = [
    ];
    this.allLabels = [
    ];
    this.titles = [
    ];
    this.chartDiv = document.createElement('div');
    this.chartDiv.style.overflow = 'hidden';
    this.legendDiv = document.createElement('div');
    this.legendDiv.style.overflow = 'hidden';
    this.balloon = new AmCharts.AmBalloon;
    this.balloon.chart = this;
    this.titleHeight = 0;
    this.prefixesOfBigNumbers = [
      {
        number: 1000,
        prefix: 'k'
      },
      {
        number: 1000000,
        prefix: 'M'
      },
      {
        number: 1000000000,
        prefix: 'G'
      },
      {
        number: 1000000000000,
        prefix: 'T'
      },
      {
        number: 1000000000000000,
        prefix: 'P'
      },
      {
        number: 1000000000000000000,
        prefix: 'E'
      },
      {
        number: 1e+21,
        prefix: 'Z'
      },
      {
        number: 1e+24,
        prefix: 'Y'
      }
    ];
    this.prefixesOfSmallNumbers = [
      {
        number: 1e-24,
        prefix: 'y'
      },
      {
        number: 1e-21,
        prefix: 'z'
      },
      {
        number: 1e-18,
        prefix: 'a'
      },
      {
        number: 1e-15,
        prefix: 'f'
      },
      {
        number: 1e-12,
        prefix: 'p'
      },
      {
        number: 1e-9,
        prefix: 'n'
      },
      {
        number: 0.000001,
        prefix: 'μ'
      },
      {
        number: 0.001,
        prefix: 'm'
      }
    ];
    'ontouchstart' in document.documentElement && (this.touchEventsEnabled = !1);
    this.panEventsEnabled = !1
  },
  drawChart: function () {
    this.destroy();
    var a = this.container.set();
    this.set = a;
    var b = this.container,
    c = this.backgroundColor,
    d = this.backgroundAlpha,
    e = this.realWidth,
    f = this.realHeight;
    void 0 != c && 0 < d && (this.background = c = AmCharts.rect(b, e - 1, f, c, d, 1, this.borderColor, this.borderAlpha), a.push(c));
    if (a = this.backgroundImage) this.path && (a = this.path + a),
    this.bgImg = b = b.image(a, 0, 0, e, f),
    this.set.push(b);
    this.drawTitles()
  },
  drawTitles: function () {
    var a = this.titles;
    if (0 < a.length) for (var b = 20, c = 0; c < a.length; c++) {
      var d = a[c],
      e = d.color;
      void 0 == e && (e = this.color);
      var f = d.size,
      g = d.alpha;
      isNaN(g) && (g = 1);
      var b = b + f / 2,
      h = this.marginLeft,
      e = AmCharts.text(this.container, h + (this.divRealWidth - this.marginRight - h) / 2, b, d.text, {
        fill: e,
        'fill-opacity': g,
        'font-size': f,
        'font-family': this.fontFamily
      }),
      b = !0;
      void 0 != d.bold && (b = d.bold);
      b && e.attr({
        'font-weight': 'bold'
      });
      d = e.getBBox();
      b = d.y + d.height + 5;
      this.set.push(e)
    }
  },
  write: function (a) {
    var b = this.balloon;
    b && !b.chart && (b.chart = this);
    this.listenersAdded || (this.addListeners(), this.listenersAdded = !0);
    this.div = a = 'object' != typeof a ? document.getElementById(a)  : a;
    a.style.overflow = 'hidden';
    var b = this.chartDiv,
    c = this.legendDiv,
    d = this.legend;
    this.measure();
    if (d) switch (d.position) {
      case 'bottom':
        a.appendChild(b);
        a.appendChild(c);
        break;
      case 'top':
        a.appendChild(c);
        a.appendChild(b);
        break;
      case 'absolute':
        c.style.position = 'absolute';
        b.style.position = 'absolute';
        void 0 != d.left && (c.style.left = d.left);
        void 0 != d.right && (c.style.right = d.right);
        void 0 != this.legend.top && (c.style.top = d.top);
        void 0 != this.legend.bottom && (c.style.bottom = d.bottom);
        a.appendChild(b);
        a.appendChild(c);
        break;
      case 'right':
        c.style.position = 'relative';
        b.style.position = 'absolute';
        a.appendChild(b);
        a.appendChild(c);
        break;
      case 'left':
        c.style.position = 'relative',
        b.style.position = 'absolute',
        a.appendChild(b),
        a.appendChild(c)
    } else a.appendChild(b);
    this.divIsFixed = AmCharts.findIfFixed(b);
    this.container = Raphael(this.chartDiv, this.realWidth, this.realHeight);
    this.initChart()
  },
  initChart: function () {
    this.previousHeight = this.realHeight;
    this.previousWidth = this.realWidth;
    var a = this.container;
    a && a.clear();
    this.redrawLabels()
  },
  renderfix: function () {
    this.container &&
    this.container.renderfix()
  },
  measure: function () {
    var a = this.div,
    b = this.chartDiv,
    c = a.offsetWidth,
    d = a.offsetHeight,
    e = this.container;
    a.clientHeight && (c = a.clientWidth, d = a.clientHeight);
    var a = AmCharts.toCoordinate(this.width, c),
    f = AmCharts.toCoordinate(this.height, d);
    if (a != this.previousWidth || f != this.previousHeight) b.style.width = a + 'px',
    b.style.height = f + 'px',
    e && e.setSize(a, f),
    this.balloon.setBounds(2, 2, a - 2, f);
    this.realWidth = a;
    this.realHeight = f;
    this.divRealWidth = c;
    this.divRealHeight = d
  },
  destroy: function () {
    AmCharts.removeSet(this.set);
    this.clearTimeOuts()
  },
  clearTimeOuts: function () {
    var a = this.timeOuts;
    if (a) for (var b = 0; b < a.length; b++) clearTimeout(a[b]);
    this.timeOuts = [
    ]
  },
  clear: function () {
    AmCharts.callMethod('clear', [
      this.chartScrollbar,
      this.scrollbarVertical,
      this.scrollbarHorizontal,
      this.chartCursor
    ]);
    this.chartCursor = this.scrollbarHorizontal = this.scrollbarVertical = this.chartScrollbar = null;
    this.clearTimeOuts();
    this.container && this.container.clear();
    AmCharts.removeChart(this)
  },
  setMouseCursor: function (a) {
    document.body.style.cursor =
    a
  },
  bringLabelsToFront: function () {
    for (var a = this.labels, b = a.length - 1; 0 <= b; b--) a[b].toFront()
  },
  redrawLabels: function () {
    this.labels = [
    ];
    for (var a = this.allLabels, b = 0; b < a.length; b++) this.drawLabel(a[b])
  },
  drawLabel: function (a) {
    var b = a.x,
    c = a.y,
    d = a.text,
    e = a.align,
    f = a.size,
    g = a.color,
    h = a.rotation,
    j = a.alpha,
    l = a.bold;
    this.container && (a = AmCharts.toCoordinate(b, this.realWidth), c = AmCharts.toCoordinate(c, this.realHeight), a || (a = 0), c || (c = 0), void 0 == g && (g = this.color), isNaN(f) && (f = this.fontSize), e || (e = 'start'), 'left' ==
    e && (e = 'start'), 'right' == e && (e = 'end'), 'center' == e && (e = 'middle', h ? c = this.realHeight - c + c / 2 : a = this.realWidth / 2 - a), void 0 == j && (j = 1), void 0 == h && (h = 0), a = AmCharts.text(this.container, a, c + f / 2, d, {
      fill: g,
      'fill-opacity': j,
      'text-anchor': e,
      'font-family': this.fontFamily,
      'font-size': f
    }), l && a.attr({
      'font-weight': 'bold'
    }), 0 != h && a.transform('...R' + h), a.toFront(), this.labels.push(a))
  },
  addLabel: function (a, b, c, d, e, f, g, h, j) {
    a = {
      x: a,
      y: b,
      text: c,
      align: d,
      size: e,
      color: f,
      alpha: h,
      rotation: g,
      bold: j
    };
    this.container && this.drawLabel(a);
    this.allLabels.push(a)
  },
  clearLabels: function () {
    for (var a = this.labels, b = a.length - 1; 0 <= b; b--) a[b].remove();
    this.labels = [
    ];
    this.allLabels = [
    ]
  },
  updateHeight: function () {
    var a = this.divRealHeight,
    b = this.legend;
    if (b) {
      var c = Number(this.legendDiv.style.height.replace('px', '')),
      b = b.position;
      if ('top' == b || 'bottom' == b) a -= c,
      0 > a && (a = 0),
      this.chartDiv.style.height = a + 'px'
    }
    return a
  },
  updateWidth: function () {
    var a = this.divRealWidth,
    b = this.divRealHeight,
    c = this.legend;
    if (c) {
      var d = Number(this.legendDiv.style.width.replace('px', '')),
      e = Number(this.legendDiv.style.height.replace('px', '')),
      c = c.position;
      if ('right' == c || 'left' == c) a -= d,
      0 > a && (a = 0),
      this.chartDiv.style.width = a + 'px',
      'left' == c ? this.chartDiv.style.left = AmCharts.findPosX(this.div) + d + 'px' : this.legendDiv.style.left = a + 'px',
      this.legendDiv.style.top = (b - e) / 2 + 'px'
    }
    return a
  },
  getTitleHeight: function () {
    var a = 0,
    b = this.titles;
    if (0 < b.length) for (var c = 0; c < b.length; c++) a += b[c].size + 12;
    return a
  },
  addTitle: function (a, b, c, d, e) {
    isNaN(b) && (b = this.fontSize + 2);
    a = {
      text: a,
      size: b,
      color: c,
      alpha: d,
      bold: e
    };
    this.titles.push(a);
    return a
  },
  addListeners: function () {
    var a = this;
    a.touchEventsEnabled && a.panEventsEnabled ? (a.chartDiv.addEventListener('touchstart', function (b) {
      a.handleTouchMove.call(a, b)
    }, !0), a.chartDiv.addEventListener('touchmove', function (b) {
      a.handleTouchMove.call(a, b)
    }, !0), a.chartDiv.addEventListener('touchstart', function (b) {
      a.handleTouchStart.call(a, b)
    }), a.chartDiv.addEventListener('touchend', function (b) {
      a.handleTouchEnd.call(a, b)
    }))  : (AmCharts.isNN && (a.chartDiv.addEventListener('mousedown', function (b) {
      a.handleMouseDown.call(a, b)
    }, !0), a.chartDiv.addEventListener('mouseover', function (b) {
      a.handleMouseOver.call(a, b)
    }, !0), a.chartDiv.addEventListener('mouseout', function (b) {
      a.handleMouseOut.call(a, b)
    }, !0)), AmCharts.isIE && (a.chartDiv.attachEvent('onmousedown', function (b) {
      a.handleMouseDown.call(a, b)
    }), a.chartDiv.attachEvent('onmouseover', function (b) {
      a.handleMouseOver.call(a, b)
    }), a.chartDiv.attachEvent('onmouseout', function (b) {
      a.handleMouseOut.call(a, b)
    })))
  },
  dispatchDataUpdatedEvent: function () {
    this.dispatchDataUpdated && (this.dispatchDataUpdated = !1, this.fire('dataUpdated', {
      type: 'dataUpdated'
    }))
  },
  drb: function () {
    var a = 'm,o,c,.,s,t,r,a,h,c,m,a'.split(',').reverse().join(''),
    b = window.location.hostname.split('.');
    if (2 <= b.length) var c = b[b.length - 2] + '.' + b[b.length - 1];
    if (c != a) {
      var a = a + '/?utm_source=swf&utm_medium=demo&utm_campaign=jsDemo',
      b = this.container.set(),
      c = AmCharts.rect(this.container, 145, 20, '#FFFFFF', 1),
      d = AmCharts.text(this.container, 2, 2, 'm,o,c,.,s,t,r,a,h,c,m,a, ,y,b, ,t,r,a,h,c'.split(',').reverse().join(''), {
        fill: '#000000',
        'font-family': 'Verdana',
        'font-size': 11,
        'text-anchor': 'start'
      });
      d.translate('5,8');
      b.push(c);
      b.push(d);
      this.set.push(b);
      b.click(function () {
        window.location.href = 'http://' + a
      });
      for (c = 0; c < b.length; c++) b[c].attr({
        cursor: 'pointer'
      })
    }
  },
  invalidateSize: function () {
    this.measure();
    if ((this.realWidth != this.previousWidth || this.realHeight != this.previousHeight) && this.chartCreated) this.legend && this.legend.invalidateSize(),
    this.initChart()
  },
  validateData: function (a) {
    this.chartCreated && (this.dataChanged = !0, this.initChart(a))
  },
  validateNow: function () {
    this.initChart()
  },
  showItem: function (a) {
    a.hidden = !1;
    this.initChart()
  },
  hideItem: function (a) {
    a.hidden = !0;
    this.initChart()
  },
  hideBalloon: function () {
    var a = this;
    a.hoverInt = setTimeout(function () {
      a.hideBalloonReal.call(a)
    }, 80)
  },
  hideBalloonReal: function () {
    this.balloon && this.balloon.hide()
  },
  showBalloon: function (a, b, c, d, e) {
    this.handleMouseMove();
    this.balloon.enabled && (this.balloon.followCursor(!1), this.balloon.changeColor(b), c || this.balloon.setPosition(d, e), this.balloon.followCursor(c), a && this.balloon.showBalloon(a))
  },
  handleTouchMove: function (a) {
    this.hideBalloon();
    var b = this.chartDiv;
    a.touches && (a = a.touches.item(0), this.mouseX = a.pageX - AmCharts.findPosX(b), this.mouseY = a.pageY - AmCharts.findPosY(b))
  },
  handleMouseOver: function () {
    AmCharts.resetMouseOver();
    this.mouseIsOver = !0
  },
  handleMouseOut: function () {
    AmCharts.resetMouseOver();
    this.mouseIsOver = !1
  },
  handleMouseMove: function (a) {
    if (this.mouseIsOver) {
      var b = this.chartDiv;
      a || (a = window.event);
      var c;
      a && (document.attachEvent && !window.opera ? (a.target || (a.target = a.srcElement), c = a.x, a = a.y)  : this.divIsFixed ? (c = a.clientX - AmCharts.findPosX(b), a = a.clientY - AmCharts.findPosY(b))  : (c = a.pageX - AmCharts.findPosX(b), a = a.pageY - AmCharts.findPosY(b)), this.mouseX = c, this.mouseY = a)
    }
  },
  handleTouchStart: function (a) {
    AmCharts.resetMouseOver();
    this.mouseIsOver = !0;
    this.handleMouseDown(a)
  },
  handleTouchEnd: function (a) {
    AmCharts.resetMouseOver();
    this.handleReleaseOutside(a)
  },
  handleReleaseOutside: function () {
  },
  handleMouseDown: function (a) {
    AmCharts.resetMouseOver();
    this.mouseIsOver =
    !0;
    a && a.preventDefault && a.preventDefault()
  },
  addLegend: function (a) {
    this.legend = a;
    a.chart = this;
    a.div = this.legendDiv;
    var b = this.handleLegendEvent;
    this.listenTo(a, 'showItem', b);
    this.listenTo(a, 'hideItem', b);
    this.listenTo(a, 'clickMarker', b);
    this.listenTo(a, 'rollOverItem', b);
    this.listenTo(a, 'rollOutItem', b);
    this.listenTo(a, 'rollOverMarker', b);
    this.listenTo(a, 'rollOutMarker', b);
    this.listenTo(a, 'clickLabel', b)
  },
  removeLegend: function () {
    this.legend = void 0
  },
  handleResize: function () {
    (AmCharts.isPercents(this.width) ||
    AmCharts.isPercents(this.height)) && this.invalidateSize();
    this.renderfix()
  }
}); AmCharts.Slice = AmCharts.Class({
  construct: function () {
  }
}); AmCharts.SerialDataItem = AmCharts.Class({
  construct: function () {
  }
}); AmCharts.GraphDataItem = AmCharts.Class({
  construct: function () {
  }
}); AmCharts.Guide = AmCharts.Class({
  construct: function () {
  }
}); AmCharts.toBoolean = function (a, b) {
  if (void 0 == a) return b;
  switch (('' + a).toLowerCase()) {
    case 'true':
    case 'yes':
    case '1':
      return !0;
    case 'false':
    case 'no':
    case '0':
    case null:
      return !1;
    default:
      return Boolean(a)
  }
};
AmCharts.formatMilliseconds = function (a, b) {
  if ( - 1 != a.indexOf('fff')) {
    var c = b.getMilliseconds(),
    d = '' + c;
    10 > c && (d = '00' + c);
    10 <= c && 100 > c && (d = '0' + c);
    a = a.replace(/fff/g, d)
  }
  return a
};
AmCharts.callMethod = function (a, b) {
  for (var c = 0; c < b.length; c++) {
    var d = b[c];
    if (d) {
      if (d[a]) d[a]();
      var e = d.length;
      if (0 < e) for (var f = 0; f < e; f++) {
        var g = d[f];
        if (g && g[a]) g[a]()
      }
    }
  }
};
AmCharts.toNumber = function (a) {
  return 'number' == typeof a ? a : Number(('' + a).replace(/[^0-9\-.]+/g, ''))
};
AmCharts.toColor = function (a) {
  if ('' != a && void 0 != a) if ( - 1 != a.indexOf(',')) for (var a = a.split(','), b = 0; b < a.length; b++) {
    var c = a[b].substring(a[b].length - 6, a[b].length);
    a[b] = '#' + c
  } else a = a.substring(a.length - 6, a.length),
  a = '#' + a;
  return a
};
AmCharts.toSvgColor = function (a, b) {
  if ('object' == typeof a) {
    void 0 == b && (b = 90);
    for (var c = b, d = 0; d < a.length; d++) c += '-' + a[d];
    return c
  }
  return a
};
AmCharts.toCoordinate = function (a, b, c) {
  var d;
  void 0 != a && (a = a.toString(), c && c < b && (b = c), d = Number(a), - 1 != a.indexOf('!') && (d = b - Number(a.substr(1))), - 1 != a.indexOf('%') && (d = b * Number(a.substr(0, a.length - 1)) / 100));
  return d
};
AmCharts.fitToBounds = function (a, b, c) {
  a < b && (a = b);
  a > c && (a = c);
  return a
};
AmCharts.isDefined = function (a) {
  return void 0 == a ? !1 : !0
};
AmCharts.stripNumbers = function (a) {
  return a.replace(/[0-9]+/g, '')
};
AmCharts.extractPeriod = function (a) {
  var b = AmCharts.stripNumbers(a),
  c = 1;
  b != a && (c = Number(a.slice(0, a.indexOf(b))));
  return {
    period: b,
    count: c
  }
};
AmCharts.resetDateToMin = function (a, b, c, d) {
  void 0 == d && (d = 1);
  var e,
  f,
  g,
  h,
  j,
  l,
  k;
  switch (b) {
    case 'YYYY':
      e = Math.floor(a.getFullYear() / c) * c;
      f = 0;
      g = 1;
      k = l = j = h = 0;
      break;
    case 'MM':
      e = a.getFullYear();
      f = Math.floor(a.getMonth() / c) * c;
      g = 1;
      k = l = j = h = 0;
      break;
    case 'WW':
      e = a.getFullYear();
      f = a.getMonth();
      b = a.getDay();
      0 == b && 0 < d && (b = 7);
      g = a.getDate() - b + d;
      k = l = j = h = 0;
      break;
    case 'DD':
      e = a.getFullYear();
      f = a.getMonth();
      g = Math.floor(a.getDate() / c) * c;
      k = l = j = h = 0;
      break;
    case 'hh':
      e = a.getFullYear();
      f = a.getMonth();
      g = a.getDate();
      h = Math.floor(a.getHours() /
      c) * c;
      k = l = j = 0;
      break;
    case 'mm':
      e = a.getFullYear();
      f = a.getMonth();
      g = a.getDate();
      h = a.getHours();
      j = Math.floor(a.getMinutes() / c) * c;
      k = l = 0;
      break;
    case 'ss':
      e = a.getFullYear();
      f = a.getMonth();
      g = a.getDate();
      h = a.getHours();
      j = a.getMinutes();
      l = Math.floor(a.getSeconds() / c) * c;
      k = 0;
      break;
    case 'fff':
      e = a.getFullYear(),
      f = a.getMonth(),
      g = a.getDate(),
      h = a.getHours(),
      j = a.getMinutes(),
      l = a.getSeconds(),
      k = Math.floor(a.getMilliseconds() / c) * c
  }
  return a = new Date(e, f, g, h, j, l, k)
};
AmCharts.getPeriodDuration = function (a, b) {
  void 0 == b && (b = 1);
  var c;
  switch (a) {
    case 'YYYY':
      c = 31622400000;
      break;
    case 'MM':
      c = 2678400000;
      break;
    case 'WW':
      c = 604800000;
      break;
    case 'DD':
      c = 86400000;
      break;
    case 'hh':
      c = 3600000;
      break;
    case 'mm':
      c = 60000;
      break;
    case 'ss':
      c = 1000;
      break;
    case 'fff':
      c = 1
  }
  return c * b
};
AmCharts.roundTo = function (a, b) {
  if (0 > b) return a;
  var c = Math.pow(10, b);
  return Math.round(a * c) / c
};
AmCharts.intervals = {
  s: {
    nextInterval: 'ss',
    contains: 1000
  },
  ss: {
    nextInterval: 'mm',
    contains: 60,
    count: 0
  },
  mm: {
    nextInterval: 'hh',
    contains: 60,
    count: 1
  },
  hh: {
    nextInterval: 'DD',
    contains: 24,
    count: 2
  },
  DD: {
    nextInterval: '',
    contains: Infinity,
    count: 3
  }
};
AmCharts.getMaxInterval = function (a, b) {
  var c = AmCharts.intervals;
  return a >= c[b].contains ? (a = Math.round(a / c[b].contains), b = c[b].nextInterval, AmCharts.getMaxInterval(a, b))  : 'ss' == b ? c[b].nextInterval : b
};
AmCharts.formatDuration = function (a, b, c, d, e, f) {
  var g = AmCharts.intervals,
  h = f.decimalSeparator;
  if (a >= g[b].contains) {
    var j = a - Math.floor(a / g[b].contains) * g[b].contains;
    'ss' == b && (j = AmCharts.formatNumber(j, f), 1 == j.split(h) [0].length && (j = '0' + j));
    if (('mm' == b || 'hh' == b) && 10 > j) j = '0' + j;
    c = j + '' + d[b] + '' + c;
    a = Math.floor(a / g[b].contains);
    b = g[b].nextInterval;
    return AmCharts.formatDuration(a, b, c, d, e, f)
  }
  'ss' == b && (a = AmCharts.formatNumber(a, f), 1 == a.split(h) [0].length && (a = '0' + a));
  if (('mm' == b || 'hh' == b) && 10 > a) a = '0' + a;
  c = a + '' +
  d[b] + '' + c;
  if (g[e].count > g[b].count) for (a = g[b].count; a < g[e].count; a++) b = g[b].nextInterval,
  'ss' == b || 'mm' == b || 'hh' == b ? c = '00' + d[b] + '' + c : 'DD' == b && (c = '0' + d[b] + '' + c);
  ':' == c.charAt(c.length - 1) && (c = c.substring(0, c.length - 1));
  return c
};
AmCharts.formatNumber = function (a, b, c, d, e) {
  a = AmCharts.roundTo(a, b.precision);
  isNaN(c) && (c = b.precision);
  var f = b.decimalSeparator,
  b = b.thousandsSeparator,
  g = 0 > a ? '-' : '',
  a = Math.abs(a),
  h = a.toString();
  if ( - 1 == h.indexOf('e')) {
    for (var h = h.split('.'), j = '', l = h[0].toString(), k = l.length; 0 <= k; k -= 3) j = k != l.length ? 0 != k ? l.substring(k - 3, k) + b + j : l.substring(k - 3, k) + j : l.substring(k - 3, k);
    void 0 != h[1] && (j = j + f + h[1]);
    void 0 != c && 0 < c && '0' != j && (j = AmCharts.addZeroes(j, f, c))
  } else j = h;
  j = g + j;
  '' == g && !0 == d && 0 != a && (j = '+' + j);
  !0 == e && (j +=
  '%');
  return j
};
AmCharts.addZeroes = function (a, b, c) {
  a = a.split(b);
  void 0 == a[1] && 0 < c && (a[1] = '0');
  return a[1].length < c ? (a[1] += '0', AmCharts.addZeroes(a[0] + b + a[1], b, c))  : void 0 != a[1] ? a[0] + b + a[1] : a[0]
};
AmCharts.scientificToNormal = function (a) {
  var b,
  a = a.toString().split('e');
  if ('-' == a[1].substr(0, 1)) {
    b = '0.';
    for (var c = 0; c < Math.abs(Number(a[1])) - 1; c++) b += '0';
    b += a[0].split('.').join('')
  } else {
    var d = 0;
    b = a[0].split('.');
    b[1] && (d = b[1].length);
    b = a[0].split('.').join('');
    for (c = 0; c < Math.abs(Number(a[1])) - d; c++) b += '0'
  }
  return b
};
AmCharts.toScientific = function (a, b) {
  if (0 == a) return '0';
  var c = Math.floor(Math.log(Math.abs(a)) * Math.LOG10E);
  Math.pow(10, c);
  mantissa = mantissa.toString().split('.').join(b);
  return mantissa.toString() + 'e' + c
};
AmCharts.generateGradient = function (a, b, c) {
  if (c) for (var d = c.length - 1; 0 <= d; d--) b += '-' + AmCharts.adjustLuminosity(a, c[d] / 100);
   else if ('object' == typeof a) if (1 < a.length) for (d = 0; d < a.length; d++) b += '-' + a[d];
   else b = a[0];
   else b = a;
  return b
};
AmCharts.randomColor = function () {
  function a() {
    return Math.floor(256 * Math.random()).toString(16)
  }
  return '#' + a() + a() + a()
};
AmCharts.hitTest = function (a, b, c) {
  var d = !1,
  e = a.x - 5,
  f = a.x + a.width + 5,
  g = a.y - 5,
  h = a.y + a.height + 5,
  j = AmCharts.isInRectangle;
  d || (d = j(e, g, b));
  d || (d = j(e, h, b));
  d || (d = j(f, g, b));
  d || (d = j(f, h, b));
  !d && !0 != c && (d = AmCharts.hitTest(b, a, !0));
  return d
};
AmCharts.isInRectangle = function (a, b, c) {
  return a >= c.x - 5 && a <= c.x + c.width + 5 && b >= c.y - 5 && b <= c.y + c.height + 5 ? !0 : !1
};
AmCharts.isPercents = function (a) {
  if ( - 1 != ('' + a).indexOf('%')) return !0
};
AmCharts.dayNames = 'Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday'.split(',');
AmCharts.shortDayNames = 'Sun,Mon,Tue,Wed,Thu,Fri,Sat'.split(',');
AmCharts.monthNames = 'January,February,March,April,May,June,July,August,September,October,November,December'.split(',');
AmCharts.shortMonthNames = 'Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec'.split(',');
AmCharts.formatDate = function (a, b) {
  var c = a.getFullYear(),
  d = ('' + c).substr( - 2, 2),
  e = a.getMonth(),
  f = e + 1;
  9 > e && (f = '0' + f);
  var g = a.getDate(),
  h = g;
  10 > g && (h = '0' + g);
  var j = a.getDay(),
  l = '0' + j,
  k = a.getHours(),
  n = k;
  24 == n && (n = 0);
  var o = n;
  10 > o && (o = '0' + o);
  b = b.replace(/JJ/g, o);
  b = b.replace(/J/g, n);
  n = k;
  0 == n && (n = 24);
  o = n;
  10 > o && (o = '0' + o);
  b = b.replace(/HH/g, o);
  b = b.replace(/H/g, n);
  n = k;
  11 < n && (n -= 12);
  o = n;
  10 > o && (o = '0' + o);
  b = b.replace(/KK/g, o);
  b = b.replace(/K/g, n);
  n = k;
  12 < n && (n -= 12);
  o = n;
  10 > o && (o = '0' + o);
  b = b.replace(/LL/g, o);
  b = b.replace(/L/g, n);
  o = n = a.getMinutes();
  10 > o && (o = '0' + o);
  b = b.replace(/NN/g, o);
  b = b.replace(/N/g, n);
  o = n = a.getSeconds();
  10 > o && (o = '0' + o);
  b = b.replace(/SS/g, o);
  b = b.replace(/S/g, n);
  o = n = a.getMilliseconds();
  10 > o && (o = '00' + o);
  100 > o && (o = '0' + o);
  var m = n;
  10 > m && (m = '00' + m);
  b = b.replace(/QQQ/g, o);
  b = b.replace(/QQ/g, m);
  b = b.replace(/Q/g, n);
  b = 12 > k ? b.replace(/A/g, 'am')  : b.replace(/A/g, 'pm');
  b = b.replace(/YYYY/g, '@IIII@');
  b = b.replace(/YY/g, '@II@');
  b = b.replace(/MMMM/g, '@XXXX@');
  b = b.replace(/MMM/g, '@XXX@');
  b = b.replace(/MM/g, '@XX@');
  b = b.replace(/M/g, '@X@');
  b = b.replace(/DD/g, '@RR@');
  b = b.replace(/D/g, '@R@');
  b = b.replace(/EEEE/g, '@PPPP@');
  b = b.replace(/EEE/g, '@PPP@');
  b = b.replace(/EE/g, '@PP@');
  b = b.replace(/E/g, '@P@');
  b = b.replace(/@IIII@/g, c);
  b = b.replace(/@II@/g, d);
  b = b.replace(/@XXXX@/g, AmCharts.monthNames[e]);
  b = b.replace(/@XXX@/g, AmCharts.shortMonthNames[e]);
  b = b.replace(/@XX@/g, f);
  b = b.replace(/@X@/g, e + 1);
  b = b.replace(/@RR@/g, h);
  b = b.replace(/@R@/g, g);
  b = b.replace(/@PPPP@/g, AmCharts.dayNames[j]);
  b = b.replace(/@PPP@/g, AmCharts.shortDayNames[j]);
  b = b.replace(/@PP@/g, l);
  return b = b.replace(/@P@/g, j)
};
AmCharts.findPosX = function (a) {
  for (var b = a.offsetLeft; a = a.offsetParent; ) b += a.offsetLeft,
  a != document.body && a != document.documentElement && (b -= a.scrollLeft);
  return b
};
AmCharts.findPosY = function (a) {
  for (var b = a.offsetTop; a = a.offsetParent; ) b += a.offsetTop,
  a != document.body && a != document.documentElement && (b -= a.scrollTop);
  return b
};
AmCharts.findIfFixed = function (a) {
  for (; a = a.offsetParent; ) if ('fixed' == a.style.position) return !0;
  return !1
};
AmCharts.formatValue = function (a, b, c, d, e, f, g, h) {
  if (b) {
    void 0 == e && (e = '');
    for (var j = 0; j < c.length; j++) {
      var l = c[j],
      k = b[l];
      void 0 != k && (k = f ? AmCharts.addPrefix(k, h, g, d)  : AmCharts.formatNumber(k, d), a = a.replace(RegExp('\\[\\[' + e + '' + l + '\\]\\]', 'g'), k))
    }
  }
  return a
};
AmCharts.formatDataContextValue = function (a, b) {
  if (a) for (var c = a.match(/\[\[.*?\]\]/g), d = 0; d < c.length; d++) {
    var e = c[d],
    e = e.substr(2, e.length - 4);
    void 0 != b[e] && (a = a.replace(RegExp('\\[\\[' + e + '\\]\\]', 'g'), b[e]))
  }
  return a
};
AmCharts.massReplace = function (a, b) {
  for (var c in b) {
    var d = b[c];
    void 0 == d && (d = '');
    a = a.replace(c, d)
  }
  return a
};
AmCharts.cleanFromEmpty = function (a) {
  return a.replace(/\[\[[^\]]*\]\]/g, '')
};
AmCharts.addPrefix = function (a, b, c, d) {
  var e = AmCharts.formatNumber(a, d),
  f = '',
  g;
  if (0 == a) return '0';
  0 > a && (f = '-');
  a = Math.abs(a);
  if (1 < a) for (g = b.length - 1; - 1 < g; g--) {
    if (a >= b[g].number) {
      a /= b[g].number;
      d = Number(d.precision);
      1 > d && (d = 1);
      a = AmCharts.roundTo(a, d);
      e = f + '' + a + '' + b[g].prefix;
      break
    }
  } else for (g = 0; g < c.length; g++) if (a <= c[g].number) {
    a /= c[g].number;
    d = Math.abs(Math.round(Math.log(a) * Math.LOG10E));
    a = AmCharts.roundTo(a, d);
    e = f + '' + a + '' + c[g].prefix;
    break
  }
  return e
};
AmCharts.removeObject = function (a, b) {
  a && a.node && (a.remove(), b && b.exclude(a))
};
AmCharts.removeSet = function (a) {
  if (a) for (var b = 0; b < a.length; b++) {
    var c = a[b];
    0 < c.length && AmCharts.removeSet(c);
    var d = c.clip,
    e = c.node;
    e && (e.clipRect && (d = e.clipRect), e.parentNode && c.remove());
    d && (d.parentNode && d.parentNode.removeChild(d), delete d)
  }
};
AmCharts.copyProperties = function (a, b) {
  for (var c in a) 'events' != c && void 0 != a[c] && 'function' != typeof a[c] && (b[c] = a[c])
};
AmCharts.recommended = function () {
  var a = 'js';
  document.implementation.hasFeature('http://www.w3.org/TR/SVG11/feature#BasicStructure', '1.1') || swfobject && swfobject.hasFlashPlayerVersion('8') && (a = 'flash');
  return a
};
AmCharts.Bezier = AmCharts.Class({
  construct: function (a, b, c, d, e, f, g, h, j, l) {
    'object' == typeof g && (g = g[0]);
    'object' == typeof h && (h = h[0]);
    var k = '';
    1 == j && (k = '.');
    1 < j && (k = '-');
    d = {
      stroke: d,
      fill: g,
      'fill-opacity': h,
      'stroke-dasharray': k,
      opacity: e,
      'stroke-width': f
    };
    e = b.length;
    this.lineArray = [
      'M',
      b[0],
      c[0]
    ];
    f = [
    ];
    for (g = 0; g < e; g++) f.push({
      x: b[g],
      y: c[g]
    });
    1 < f.length && this.drawBeziers(this.interpolate(f));
    this.lineArray = this.lineArray.concat(l);
    this.path = a.path(this.lineArray).attr(d)
  },
  interpolate: function (a) {
    var b = [
    ];
    b.push({
      x: a[0].x,
      y: a[0].y
    });
    var c = a[1].x - a[0].x,
    d = a[1].y - a[0].y;
    b.push({
      x: a[0].x + c / 6,
      y: a[0].y + d / 6
    });
    for (var e = 1; e < a.length - 1; e++) {
      var f = a[e - 1],
      g = a[e],
      d = a[e + 1],
      c = d.x - g.x,
      d = d.y - f.y,
      f = g.x - f.x;
      f > c && (f = c);
      b.push({
        x: g.x - f / 3,
        y: g.y - d / 6
      });
      b.push({
        x: g.x,
        y: g.y
      });
      b.push({
        x: g.x + f / 3,
        y: g.y + d / 6
      })
    }
    d = a[a.length - 1].y - a[a.length - 2].y;
    c = a[a.length - 1].x - a[a.length - 2].x;
    b.push({
      x: a[a.length - 1].x - c / 3,
      y: a[a.length - 1].y - d / 6
    });
    b.push({
      x: a[a.length - 1].x,
      y: a[a.length - 1].y
    });
    return b
  },
  drawBeziers: function (a) {
    for (var b = 0; b < (a.length -
    1) / 3; b++) this.drawBezierMidpoint(a[3 * b], a[3 * b + 1], a[3 * b + 2], a[3 * b + 3])
  },
  drawBezierMidpoint: function (a, b, c, d) {
    var e = this.getPointOnSegment(a, b, 0.75),
    f = this.getPointOnSegment(d, c, 0.75),
    g = (d.x - a.x) / 16,
    h = (d.y - a.y) / 16,
    a = this.getPointOnSegment(a, b, 0.375),
    b = this.getPointOnSegment(e, f, 0.375);
    b.x -= g;
    b.y -= h;
    var j = this.getPointOnSegment(f, e, 0.375);
    j.x += g;
    j.y += h;
    c = this.getPointOnSegment(d, c, 0.375);
    g = this.getMiddle(a, b);
    e = this.getMiddle(e, f);
    f = this.getMiddle(j, c);
    h = this.lineArray;
    h.push('Q', a.x, a.y, g.x, g.y);
    h.push('Q', b.x, b.y, e.x, e.y);
    h.push('Q', j.x, j.y, f.x, f.y);
    h.push('Q', c.x, c.y, d.x, d.y)
  },
  getMiddle: function (a, b) {
    return {
      x: (a.x + b.x) / 2,
      y: (a.y + b.y) / 2
    }
  },
  getPointOnSegment: function (a, b, c) {
    return {
      x: a.x + (b.x - a.x) * c,
      y: a.y + (b.y - a.y) * c
    }
  }
});
AmCharts.Cuboid = AmCharts.Class({
  construct: function (a, b, c, d, e, f, g, h, j, l, k, n) {
    this.set = a.set();
    this.container = a;
    this.h = c;
    this.w = b;
    this.dx = d;
    this.dy = e;
    this.colors = f;
    this.alpha = g;
    this.bwidth = h;
    this.bcolor = j;
    this.balpha = l;
    'object' != typeof f && (this.colors = [
      f
    ]);
    0 > b && 0 == k && (k = 180);
    0 > c && 270 == k && (k = 90);
    this.gradientRotation = k;
    0 == d && 0 == e && (this.cornerRadius = n);
    this.draw()
  },
  draw: function () {
    var a = this.set;
    a.remove();
    var b = this.container,
    c = Math.abs(this.w),
    d = Math.abs(this.h),
    e = this.dx,
    f = this.dy,
    g = this.colors,
    h =
    this.alpha,
    j = this.bwidth,
    l = this.bcolor,
    k = this.balpha,
    n = this.gradientRotation,
    o = this.cornerRadius;
    if (0 < e || 0 < f) {
      var m = g[g.length - 1];
      0 < d && (m = g[0]);
      m = AmCharts.adjustLuminosity(m, - 0.2);
      m = AmCharts.polygon(b, [
        0,
        e,
        c + e,
        c,
        0
      ], [
        0,
        f,
        f,
        0,
        0
      ], [
        m
      ], h, 0, 0, 0, n);
      a.push(m);
      m = AmCharts.line(b, [
        0,
        e,
        c + e
      ], [
        0,
        f,
        f
      ], l, k, j);
      a.push(m);
      m = AmCharts.adjustLuminosity(g[0], - 0.2);
      if (0 < d && 0 < c) {
        var p = AmCharts.rect(b, c, d, m, h, 0, 0, 0, 0, n);
        a.push(p);
        p.translate(e, - d + f);
        p = AmCharts.line(b, [
          e,
          e
        ], [
          f,
          - d + f
        ], l, k, j);
        a.push(p);
        p = AmCharts.polygon(b, [
          0,
          0,
          e,
          e,
          0
        ], [
          0,
          - d,
          - d + f,
          f,
          0
        ], m, h, 0, 0, 0, n);
        a.push(p);
        m = AmCharts.polygon(b, [
          0,
          0,
          e,
          e,
          0
        ], [
          0,
          - d,
          - d + f,
          f,
          0
        ], m, h, 0, 0, 0, n);
        m.translate(c, 0);
        a.push(m);
        m = AmCharts.line(b, [
          0,
          e,
          e,
          0
        ], [
          - d,
          - d + f,
          f,
          0
        ], l, k, j);
        a.push(m);
        m.translate(c, 0)
      }
      m = g[0];
      0 < d && (m = g[g.length - 1]);
      m = AmCharts.adjustLuminosity(m, 0.2);
      m = AmCharts.polygon(b, [
        0,
        e,
        c + e,
        c,
        0
      ], [
        0,
        f,
        f,
        0,
        0
      ], [
        m
      ], h, 0, 0, 0, n);
      m.translate(0, - d);
      a.push(m);
      e = AmCharts.line(b, [
        0,
        e,
        c + e
      ], [
        0,
        f,
        f
      ], l, k, j);
      e.translate(0, - d);
      a.push(e)
    }
    1 > d && (d = 1, k = h = 0);
    b = AmCharts.rect(b, c, d, g, h, j, l, k, o, n);
    b.attr({
      y: - d
    });
    a.push(b);
    this.front = b
  },
  y: function (a) {
    var b = this.set;
    0 != this.dx && 0 != this.dy ? 0 < this.h ? b.translate(0, a + this.h)  : b.translate(0, a)  : 0 > this.h ? this.set.attr({
      y: a + this.h
    })  : this.set.attr({
      y: a
    })
  },
  x: function (a) {
    var b = this.set;
    0 != this.dx && 0 != this.dy ? 0 > this.w ? b.translate(a + this.w, 0)  : b.translate(a, 0)  : 0 > this.w ? b.attr({
      x: a + this.w
    })  : b.attr({
      x: a
    })
  },
  width: function (a) {
    this.w = a;
    this.draw()
  },
  height: function (a) {
    this.h = a;
    this.draw()
  },
  getX: function () {
    return this.front.getBBox().x
  },
  getY: function () {
    return this.front.getBBox().y
  }
});
AmCharts.AmLegend = AmCharts.Class({
  construct: function () {
    this.createEvents('rollOverMarker', 'rollOverItem', 'rollOutMarker', 'rollOutItem', 'showItem', 'hideItem', 'clickMarker', 'rollOverItem', 'rollOutItem', 'clickLabel');
    this.position = 'bottom';
    this.borderColor = this.color = '#000000';
    this.borderAlpha = 0;
    this.markerLabelGap = 5;
    this.verticalGap = 10;
    this.align = 'left';
    this.horizontalGap = 0;
    this.spacing = 10;
    this.markerDisabledColor = '#AAB3B3';
    this.markerType = 'square';
    this.markerSize = 16;
    this.markerBorderAlpha = 0;
    this.markerBorderThickness =
    1;
    this.marginBottom = this.marginTop = 0;
    this.marginLeft = this.marginRight = 20;
    this.autoMargins = !0;
    this.valueWidth = 50;
    this.switchable = !0;
    this.switchType = 'x';
    this.switchColor = '#FFFFFF';
    this.rollOverColor = '#CC0000';
    this.selectedColor;
    this.reversedOrder = !1;
    this.labelText = '[[title]]';
    this.valueText = '[[value]]';
    this.useMarkerColorForLabels = !1;
    this.rollOverGraphAlpha = 1;
    this.textClickEnabled = !1;
    this.equalWidths = !0;
    this.dateFormat = 'DD-MM-YYYY';
    this.ly;
    this.lx
  },
  setData: function (a) {
    this.data = a;
    this.invalidateSize()
  },
  invalidateSize: function () {
    this.destroy();
    this.entries = [
    ];
    this.valueLabels = [
    ];
    var a = this.data;
    a && 0 < a.length && this.drawLegend();
    this.container && this.container.renderfix()
  },
  drawLegend: function () {
    var a = this.chart,
    b = this.position,
    c = this.width,
    d = a.realWidth,
    e = a.realHeight,
    f = this.div,
    g = this.data;
    'right' == b || 'left' == b ? (this.maxColumns = 1, this.marginLeft = this.marginRight = 10)  : this.autoMargins && (this.marginRight = a.marginRight, this.marginLeft = a.marginLeft, 'bottom' == b ? (this.marginBottom = a.autoMarginOffset, this.marginTop =
    0)  : (this.marginTop = a.autoMarginOffset, this.marginBottom = 0));
    this.divWidth = b = void 0 != c ? AmCharts.toCoordinate(c, d)  : a.realWidth;
    f.style.width = b + 'px';
    this.container ? this.container.setSize(b, e)  : this.container = Raphael(f, b, e);
    this.lx = 0;
    this.ly = 8;
    e = this.markerSize;
    0 < e && (this.lx += e + this.markerLabelGap, this.ly = e / 2);
    this.titleWidth = 0;
    if (e = this.title) f = a.fontSize,
    this.titleWidth = AmCharts.text(this.container, 0, this.marginTop + f / 2 + this.ly, e, {
      fill: this.color,
      'text-anchor': 'start',
      'font-weight': 'bold',
      'font-family': a.fontFamily,
      'font-size': f
    }).getBBox().width + 15;
    for (a = this.index = this.maxLabelWidth = 0; a < g.length; a++) this.createEntry(g[a]);
    for (a = this.index = 0; a < g.length; a++) this.createValue(g[a]);
    this.arrangeEntries();
    this.updateValues()
  },
  arrangeEntries: function () {
    var a = this.position,
    b = this.marginLeft + this.titleWidth,
    c = this.marginRight,
    d = this.marginTop,
    e = this.marginBottom,
    f = this.horizontalGap,
    g = this.div,
    h = this.divWidth,
    j = this.maxColumns,
    l = this.verticalGap,
    k = this.spacing,
    n = h - c - b,
    o = 0,
    m = 0,
    p = this.container.set();
    this.set = p;
    for (var s =
    this.entries, q = 0; q < s.length; q++) {
      var r = s[q].getBBox(),
      v = r.width;
      v > o && (o = v);
      r = r.height;
      r > m && (m = r)
    }
    for (var y = v = 0, B = f, q = 0; q < s.length; q++) {
      var J = s[q];
      this.reversedOrder && (J = s[s.length - q - 1]);
      var r = J.getBBox(),
      t;
      this.equalWidths ? t = f + y * (o + k + this.markerLabelGap)  : (t = B, B = B + r.width + f + k);
      t + r.width > n && 0 < q && (v++, y = 0, t = f, B = t + r.width + f + k);
      J.translate(t + ',' + (l + (m + l) * v));
      y++;
      !isNaN(j) && y >= j && (y = 0, v++);
      p.push(J)
    }
    r = p.getBBox();
    j = r.height + 2 * l - 1;
    'left' == a || 'right' == a ? (f = r.width + 2 * f, g.style.width = f + b + c + 'px')  : f = h - b - c - 1;
    c = AmCharts.rect(this.container, f, j, this.backgroundColor, this.backgroundAlpha, 1, this.borderColor, this.borderAlpha);
    c.toBack();
    p.push(c);
    p.translate(b + ',' + d);
    if ('top' == a || 'bottom' == a) p.pop(),
    'center' == this.align && p.translate((f - r.width) / 2 + ',0'),
    'right' == this.align && p.translate(f - r.width + ',0');
    a = j + d + e + 1;
    0 > a && (a = 0);
    g.style.height = a + 'px'
  },
  createEntry: function (a) {
    if (!1 !== a.visibleInLegend) {
      var b = this,
      c = b.chart,
      d = a.markerType;
      d || (d = b.markerType);
      var e = a.color,
      f = a.alpha;
      a.legendKeyColor && (e = a.legendKeyColor());
      a.legendKeyAlpha && (f = a.legendKeyAlpha());
      !0 == a.hidden && (e = b.markerDisabledColor);
      if (d = b.createMarker(d, e, f)) if (0 < d.length) for (f = 0; f < d.length; f++) d[f].dItem = a;
       else d.dItem = a;
      if (f = b.switchType) var g = 'x' == f ? b.createX()  : b.createV();
      g.dItem = a;
      !0 != a.hidden ? 'x' == f ? g.hide()  : g.show()  : 'x' != f && g.hide();
      b.switchable || g.hide();
      f = b.container.set([d,
      g]);
      c.touchEventsEnabled ? (f.touchend(function () {
        b.clickMarker(this.dItem)
      }), f.touchstart(function () {
        b.rollOverMarker(this.dItem)
      }))  : f.hover(function () {
        b.rollOverMarker(this.dItem)
      }, function () {
        b.rollOutMarker(this.dItem)
      }).click(function () {
        b.clickMarker(this.dItem)
      });
      c = b.color;
      a.showBalloon && b.textClickEnabled && void 0 != b.selectedColor && (c = b.selectedColor);
      b.useMarkerColorForLabels && (c = e);
      !0 == a.hidden && (c = b.markerDisabledColor);
      e = b.chart.fontSize;
      isNaN(b.fontSize) || (e = b.fontSize);
      if (f = AmCharts.massReplace(b.labelText, {
        '[[title]]': a.title
      })) {
        var h = AmCharts.text(b.container, b.lx, b.ly, f, {
          fill: c,
          'text-anchor': 'start',
          'font-family': b.chart.fontFamily,
          'font-size': e
        }),
        e = h.getBBox().width;
        b.maxLabelWidth < e && (b.maxLabelWidth = e)
      }
      e = b.container.set();
      d && e.push(d);
      g && e.push(g);
      h && e.push(h);
      b.entries[b.index] = e;
      a.legendEntry = b.entries[b.index];
      a.legendLabel = h;
      a.legendSwitch = g;
      b.index++
    }
  },
  rollOverMarker: function (a) {
    this.switchable && this.dispatch('rollOverMarker', a);
    this.dispatch('rollOverItem', a)
  },
  rollOutMarker: function (a) {
    this.switchable && this.dispatch('rollOutMarker', a);
    this.dispatch('rollOutItem', a)
  },
  clickMarker: function (a) {
    this.switchable ? !0 == a.hidden ? this.dispatch('showItem', a)  : this.dispatch('hideItem', a)  : this.dispatch('clickMarker', a)
  },
  rollOverLabel: function (a) {
    a.hidden || (this.textClickEnabled && a.legendLabel && a.legendLabel.attr({
      fill: this.rollOverColor
    }), this.dispatch('rollOverItem', a))
  },
  rollOutLabel: function (a) {
    if (!a.hidden) {
      if (this.textClickEnabled && a.legendLabel) {
        var b = this.color;
        void 0 != this.selectedColor && a.showBalloon && (b = this.selectedColor);
        this.useMarkerColorForLabels && (b = a.lineColor, void 0 == b && (b = a.color));
        a.legendLabel.attr({
          fill: b
        })
      }
      this.dispatch('rollOutItem', a)
    }
  },
  clickLabel: function (a) {
    this.textClickEnabled ?
    a.hidden || this.dispatch('clickLabel', a)  : this.switchable && (!0 == a.hidden ? this.dispatch('showItem', a)  : this.dispatch('hideItem', a))
  },
  dispatch: function (a, b) {
    this.fire(a, {
      type: a,
      dataItem: b
    })
  },
  createValue: function (a) {
    var b = this;
    if (!1 !== a.visibleInLegend) {
      var c = b.maxLabelWidth;
      b.equalWidths || (b.valueAlign = 'left');
      'left' == b.valueAlign && (c = a.legendEntry.getBBox().width);
      var d = c;
      if (b.valueText) {
        var e = b.color;
        b.useMarkerColorForLabels && (e = a.color);
        !0 == a.hidden && (e = b.markerDisabledColor);
        var f = b.chart.fontSize;
        isNaN(b.fontSize) && (f = b.fontSize);
        var g = b.valueText,
        c = c + b.lx + b.markerLabelGap + b.valueWidth,
        h = 'end';
        'left' == b.valueAlign && (c -= b.valueWidth, h = 'start');
        e = AmCharts.text(b.container, c, b.ly, g, {
          fill: e,
          'text-anchor': h,
          'font-family': b.chart.fontFamily,
          'font-size': f
        });
        b.entries[b.index].push(e);
        d += b.valueWidth + b.markerLabelGap;
        e.dItem = a;
        b.valueLabels.push(e)
      }
      b.index++;
      d = b.container.rect(b.markerSize + b.markerLabelGap, 0, d, b.markerSize).attr({
        stroke: 'none',
        fill: '#FFCCFF',
        'fill-opacity': 0
      });
      d.dItem = a;
      b.entries[b.index -
      1].push(d);
      d.mouseover(function () {
        b.rollOverLabel(this.dItem)
      }).mouseout(function () {
        b.rollOutLabel(this.dItem)
      }).click(function () {
        b.clickLabel(this.dItem)
      })
    }
  },
  createV: function () {
    var a = this.markerSize;
    return this.container.path(['M',
    a / 5,
    a / 3,
    'L',
    a / 2,
    a - a / 5,
    'L',
    a - a / 5,
    a / 5,
    'L',
    a / 2,
    a / 1.7,
    'Z']).attr({
      fill: this.switchColor,
      stroke: this.switchColor
    })
  },
  createX: function () {
    var a = this.markerSize - 3;
    return this.container.path(['M',
    3,
    3,
    'L',
    a,
    a,
    'M',
    a,
    3,
    'L',
    3,
    a]).attr({
      stroke: this.switchColor,
      'stroke-width': 3
    })
  },
  createMarker: function (a, b, c) {
    var d = this.markerSize,
    e = this.container,
    f,
    g = this.markerBorderColor;
    g || (g = b);
    c = {
      fill: b,
      stroke: g,
      opacity: c,
      'stroke-opacity': this.markerBorderAlpha,
      'stroke-width': this.markerBorderThickness
    };
    switch (a) {
      case 'square':
        f = e.rect(0, 0, d, d).attr(c);
        break;
      case 'circle':
        f = e.circle(d / 2, d / 2, d / 2).attr(c);
        break;
      case 'line':
        f = e.path(['M',
        0,
        d / 2,
        'L',
        d,
        d / 2]).attr({
          stroke: b,
          'stroke-width': this.markerBorderThickness
        });
        break;
      case 'dashedLine':
        f = e.path(['M',
        0,
        d / 2,
        'L',
        d / 2 - 2,
        d / 2,
        'M',
        d / 2 + 2,
        d / 2,
        'L',
        d,
        d / 2]).attr({
          stroke: b,
          'stroke-width': this.markerBorderThickness
        });
        break;
      case 'triangleUp':
        f = e.path(['M',
        0,
        d,
        'L',
        d / 2,
        0,
        'L',
        d,
        d,
        'L',
        0,
        d,
        'Z']).attr(c);
        break;
      case 'triangleDown':
        f = e.path(['M',
        0,
        0,
        'L',
        d / 2,
        d,
        'L',
        d,
        0,
        'L',
        0,
        0,
        'Z']).attr(c);
        break;
      case 'bubble':
        c.fill = NaN,
        c.gradient = 'r' + b + '-' + AmCharts.adjustLuminosity(b, - 0.4),
        f = e.circle(d / 2, d / 2, d / 2).attr(c)
    }
    return f
  },
  validateNow: function () {
    this.invalidateSize()
  },
  updateValues: function () {
    for (var a = this.valueLabels, b = this.chart, c = 0; c < a.length; c++) {
      var d = a[c],
      e = d.dItem;
      if (void 0 != e.type) {
        var f =
        e.currentDataItem;
        if (f) {
          var g = this.valueText;
          e.legendValueText && (g = e.legendValueText);
          e = g;
          e = b.formatString(e, f);
          d.attr({
            text: e
          })
        } else d.attr({
          text: ' '
        })
      } else f = b.formatString(this.valueText, e),
      d.attr({
        text: f
      })
    }
  },
  destroy: function () {
    var a = this.container;
    a && a.clear()
  }
}); AmCharts.AmBalloon = AmCharts.Class({
  construct: function () {
    this.enabled = !0;
    this.fillColor = '#CC0000';
    this.fillAlpha = 1;
    this.borderThickness = 2;
    this.borderColor = '#FFFFFF';
    this.borderAlpha = 1;
    this.cornerRadius = 6;
    this.maximumWidth = 220;
    this.horizontalPadding = 8;
    this.verticalPadding = 5;
    this.pointerWidth = 10;
    this.pointerOrientation = 'vertical';
    this.color = '#FFFFFF';
    this.textShadowColor = '#000000';
    this.adjustBorderColor = !1;
    this.showBullet = !0;
    this.show = this.follow = !1;
    this.bulletSize = 3
  },
  draw: function () {
    var a = this.pointToX,
    b = this.pointToY;
    if (!isNaN(a)) {
      var c = this.chart,
      d = c.container;
      AmCharts.removeSet(this.set);
      var e = d.set();
      this.set = e;
      if (this.show) {
        var f = this.l,
        g = this.t,
        h = this.r,
        j = this.b,
        l = this.textShadowColor;
        this.color == l && (l = null);
        var k = this.balloonColor,
        n = this.fillColor,
        o = this.borderColor;
        void 0 != k && (this.adjustBorderColor ? o = k : n = k);
        var m = this.horizontalPadding,
        p = this.verticalPadding,
        k = this.pointerWidth,
        s = this.pointerOrientation,
        q = this.cornerRadius,
        r = c.fontFamily,
        v = this.fontSize;
        void 0 == v && (v = c.fontSize);
        c = AmCharts.text(d, 0, 0, this.text, {
          fill: this.color,
          'font-family': r,
          'font-size': v
        });
        e.push(c);
        if (void 0 != l) {
          var y = AmCharts.text(d, 1, 1, this.text, {
            fill: l,
            opacity: 0.4,
            'font-family': r,
            'font-size': v
          });
          e.push(y)
        }
        l = c.getBBox();
        e = l.height + 2 * p;
        p = l.width + 2 * m;
        window.opera && (e += 6);
        c.translate(p / 2 + ',' + e / 2);
        y && y.translate(p / 2 + ',' + e / 2);
        'horizontal' != s ? (m = a - p / 2, l = b < g + e + 10 && 'down' != s ? b + k : b - e - k)  : (2 * k > e && (k = e / 2), l = b - e / 2, m = a < f + (h - f) / 2 ? a + k : a - p - k);
        l + e >= j && (l = j - e);
        l < g && (l = g);
        m < f && (m = f);
        m + p > h && (m = h - p);
        if (0 < q) {
          if (o = AmCharts.rect(d, p, e, [
            n
          ], [
            this.fillAlpha
          ], this.borderThickness, o, this.borderAlpha, this.cornerRadius), this.showBullet) {
            var B = AmCharts.circle(d, this.bulletSize, n, this.fillAlpha);
            B.translate(a + ',' + b)
          }
        } else j = [
        ],
        q = [
        ],
        'horizontal' != s ? (f = a - m, f > p - k && (f = p - k), f < k && (f = k), j = [
          0,
          f - k,
          a - m,
          f + k,
          p,
          p,
          0,
          0
        ], q = b < g + e + 10 && 'down' != s ? [
          0,
          0,
          b - l + 3,
          0,
          0,
          e,
          e,
          0
        ] : [
          e,
          e,
          b - l - 3,
          e,
          e,
          0,
          0,
          e
        ])  : (g = b - l, g > e - k && (g = e - k), g < k && (g = k), q = [
          0,
          g - k,
          b - l,
          g + k,
          e,
          e,
          0,
          0
        ], j = a < f + (h - f) / 2 ? [
          0,
          0,
          a - m,
          0,
          0,
          p,
          p,
          0
        ] : [
          p,
          p,
          a - m,
          p,
          p,
          0,
          0,
          p
        ]),
        o = AmCharts.polygon(d, j, q, n, this.fillAlpha, this.borderThickness, o, this.borderAlpha);
        this.set.push(o);
        o.toFront();
        y && y.toFront();
        c.toFront();
        this.set.translate(m + ',' + l);
        l = o.getBBox();
        this.bottom = l.y + l.height;
        this.yPos = l.y;
        B && this.set.push(B)
      }
    }
  },
  followMouse: function () {
    if (this.follow && this.show) {
      var a = this.chart.mouseX,
      b = this.chart.mouseY;
      this.pointToX = a;
      this.pointToY = b;
      if (a != this.previousX || b != this.previousY) if (this.previousX = a, this.previousY = b, 0 == this.cornerRadius) this.draw();
       else {
        var c = this.set;
        if (c && c[0].node) {
          var d = c.getBBox(),
          a = a - d.width / 2,
          e = b - d.height - 10;
          a < this.l && (a = this.l);
          a > this.r - d.width && (a = this.r - d.width);
          e < this.t && (e = b + 10);
          c.translate(a - d.x + ',' + (e - d.y))
        }
      }
    }
  },
  changeColor: function (a) {
    this.balloonColor = a
  },
  setBounds: function (a, b, c, d) {
    this.l = a;
    this.t = b;
    this.r = c;
    this.b = d
  },
  showBalloon: function (a) {
    this.text = a;
    this.show = !0;
    this.draw()
  },
  hide: function () {
    this.destroy();
    this.follow = this.show = !1
  },
  setPosition: function (a, b, c) {
    this.pointToX = a;
    this.pointToY = b;
    c && (a != this.previousX || b != this.previousY) && this.draw();
    this.previousX = a;
    this.previousY = b
  },
  followCursor: function (a) {
    var b =
    this;
    (b.follow = a) ? (b.pShowBullet = b.showBullet, b.showBullet = !1)  : void 0 != b.pShowBullet && (b.showBullet = b.pShowBullet);
    clearInterval(b.interval);
    var c = b.chart.mouseX,
    d = b.chart.mouseY;
    !isNaN(c) && a && (b.pointToX = c, b.pointToY = d, b.interval = setInterval(function () {
      b.followMouse.call(b)
    }, 40))
  },
  destroy: function () {
    clearInterval(this.interval);
    AmCharts.removeSet(this.set)
  }
}); AmCharts.AmCoordinateChart = AmCharts.Class({
  inherits: AmCharts.AmChart,
  construct: function () {
    AmCharts.AmCoordinateChart.base.construct.call(this);
    this.createEvents('rollOverGraphItem', 'rollOutGraphItem', 'clickGraphItem', 'doubleClickGraphItem');
    this.plotAreaFillColors = '#FFFFFF';
    this.plotAreaFillAlphas = 0;
    this.plotAreaBorderColor = '#000000';
    this.startDuration = this.startAlpha = this.plotAreaBorderAlpha = 0;
    this.startEffect = 'elastic';
    this.sequencedAnimation = !0;
    this.colors = '#FF6600,#FCD202,#B0DE09,#0D8ECF,#2A0CD0,#CD0D74,#CC0000,#00CC00,#0000CC,#DDDDDD,#999999,#333333,#990000'.split(',');
    this.balloonDateFormat = 'MMM DD, YYYY';
    this.valueAxes = [
    ];
    this.graphs = [
    ]
  },
  initChart: function () {
    AmCharts.AmCoordinateChart.base.initChart.call(this);
    this.createValueAxes();
    var a = this.legend;
    a && a.setData(this.graphs)
  },
  createValueAxes: function () {
    0 == this.valueAxes.length && this.addValueAxis(new AmCharts.ValueAxis)
  },
  parseData: function () {
    this.processValueAxes();
    this.processGraphs()
  },
  parseSerialData: function () {
    AmCharts.AmSerialChart.base.parseData.call(this);
    var a = this.graphs,
    b = this.seriesIdField;
    b || (b = this.categoryField);
    this.chartData = [
    ];
    var c = this.dataProvider;
    if (c) {
      var d = !1;
      this.categoryAxis && (d = this.categoryAxis.parseDates);
      if (d) var e = AmCharts.extractPeriod(this.categoryAxis.minPeriod),
      f = e.period,
      e = e.count;
      var g = {
      };
      this.lookupTable = g;
      for (var h = 0; h < c.length; h++) {
        var j = {
        },
        l = c[h],
        k = l[this.categoryField];
        j.category = k;
        g[l[b]] = j;
        d && (k = new Date(k), k = AmCharts.resetDateToMin(k, f, e), j.category = k, j.time = k.getTime());
        var n = this.valueAxes;
        j.axes = {
        };
        j.x = {
        };
        for (var o = 0; o < n.length; o++) {
          var m = n[o].id;
          j.axes[m] = {
          };
          j.axes[m].graphs =
          {
          };
          for (var p = 0; p < a.length; p++) {
            var k = a[p],
            s = k.id,
            q = k.periodValue;
            if (k.valueAxis.id == m) {
              j.axes[m].graphs[s] = {
              };
              var r = {
              };
              r.index = h;
              r.values = this.processValues(l, k, q);
              this.processFields(k, r, l);
              r.category = j.category;
              r.serialDataItem = j;
              r.graph = k;
              j.axes[m].graphs[s] = r
            }
          }
        }
        this.chartData[h] = j
      }
    }
    for (b = 0; b < a.length; b++) k = a[b],
    k.dataProvider && this.parseGraphData(k)
  },
  processValues: function (a, b, c) {
    var d = {
    },
    e = Number(a[b.valueField + c]);
    isNaN(e) || (d.value = e);
    e = Number(a[b.openField + c]);
    isNaN(e) || (d.open = e);
    e = Number(a[b.closeField +
    c]);
    isNaN(e) || (d.close = e);
    e = Number(a[b.lowField + c]);
    isNaN(e) || (d.low = e);
    e = Number(a[b.highField + c]);
    isNaN(e) || (d.high = e);
    return d
  },
  parseGraphData: function (a) {
    var b = a.dataProvider,
    c = a.seriesIdField;
    c || (c = this.seriesIdField);
    c || (c = this.categoryField);
    for (var d = 0; d < b.length; d++) {
      var e = b[d],
      f = this.lookupTable['' + e[c]],
      g = a.valueAxis.id;
      f && (g = f.axes[g].graphs[a.id], g.serialDataItem = f, g.values = this.processValues(e, a, a.periodValue), this.processFields(a, g, e))
    }
  },
  addValueAxis: function (a) {
    a.chart = this;
    this.valueAxes.push(a);
    this.validateData()
  },
  removeValueAxesAndGraphs: function () {
    for (var a = this.valueAxes, b = a.length - 1; - 1 < b; b--) this.removeValueAxis(a[b])
  },
  removeValueAxis: function (a) {
    var b = this.graphs,
    c;
    for (c = b.length - 1; 0 <= c; c--) {
      var d = b[c];
      d && d.valueAxis == a && this.removeGraph(d)
    }
    b = this.valueAxes;
    for (c = b.length - 1; 0 <= c; c--) b[c] == a && b.splice(c, 1);
    this.validateData()
  },
  addGraph: function (a) {
    this.graphs.push(a);
    this.chooseGraphColor(a, this.graphs.length - 1);
    this.validateData()
  },
  removeGraph: function (a) {
    for (var b = this.graphs, c = b.length -
    1; 0 <= c; c--) b[c] == a && (b.splice(c, 1), a.destroy());
    this.validateData()
  },
  processValueAxes: function () {
    for (var a = this.valueAxes, b = 0; b < a.length; b++) {
      var c = a[b];
      c.chart = this;
      c.id || (c.id = 'valueAxis' + b);
      if (!0 === this.usePrefixes || !1 === this.usePrefixes) c.usePrefixes = this.usePrefixes
    }
  },
  processGraphs: function () {
    for (var a = this.graphs, b = 0; b < a.length; b++) {
      var c = a[b];
      c.chart = this;
      c.valueAxis || (c.valueAxis = this.valueAxes[0]);
      c.id || (c.id = 'graph' + b)
    }
  },
  formatString: function (a, b) {
    var c = b.graph,
    d = c.valueAxis;
    d.duration &&
    b.values.value && (d = AmCharts.formatDuration(b.values.value, d.duration, '', d.durationUnits, d.maxInterval, d.numberFormatter), a = a.split('[[value]]').join(d));
    a = AmCharts.massReplace(a, {
      '[[title]]': c.title,
      '[[description]]': b.description,
      '<br>': '\n'
    });
    return a = AmCharts.cleanFromEmpty(a)
  },
  getBalloonColor: function (a, b) {
    var c = a.lineColor,
    d = a.balloonColor,
    e = a.fillColors;
    'object' == typeof e ? c = e[0] : void 0 != e && (c = e);
    if (b.isNegative) {
      var e = a.negativeLineColor,
      f = a.negativeFillColors;
      'object' == typeof f ? e = f[0] : void 0 !=
      f && (e = f);
      void 0 != e && (c = e)
    }
    void 0 != b.color && (c = b.color);
    void 0 == d && (d = c);
    return d
  },
  getGraphById: function (a) {
    for (var b, c = this.graphs, d = 0; d < c.length; d++) {
      var e = c[d];
      e.id == a && (b = e)
    }
    return b
  },
  processFields: function (a, b, c) {
    if (a.itemColors) {
      var d = a.itemColors,
      e = b.index;
      b.color = e < d.length ? d[e] : AmCharts.randomColor()
    }
    d = 'color,alpha,fillColors,description,bullet,customBullet,bulletSize,bulletConfig,url'.split(',');
    for (e = 0; e < d.length; e++) {
      var f = d[e],
      g = a[f + 'Field'];
      g && (g = c[g], AmCharts.isDefined(g) && (b[f] = g))
    }
    b.dataContext =
    c
  },
  chooseGraphColor: function (a, b) {
    if (void 0 == a.lineColor) {
      var c;
      c = this.colors.length > b ? this.colors[b] : AmCharts.randomColor();
      a.lineColor = c
    }
  },
  handleLegendEvent: function (a) {
    var b = a.type;
    if (a = a.dataItem) {
      var c = a.hidden,
      d = a.showBalloon;
      switch (b) {
        case 'clickMarker':
          d ? this.hideGraphsBalloon(a)  : this.showGraphsBalloon(a);
          break;
        case 'clickLabel':
          d ? this.hideGraphsBalloon(a)  : this.showGraphsBalloon(a);
          break;
        case 'rollOverItem':
          c || this.highlightGraph(a);
          break;
        case 'rollOutItem':
          c || this.unhighlightGraph();
          break;
        case 'hideItem':
          this.hideGraph(a);
          break;
        case 'showItem':
          this.showGraph(a)
      }
    }
  },
  highlightGraph: function (a) {
    var b = this.graphs,
    c,
    d = 0.2;
    this.legend && (d = this.legend.rollOverGraphAlpha);
    for (c = 0; c < b.length; c++) {
      var e = b[c];
      e != a && e.changeOpacity(d)
    }
  },
  unhighlightGraph: function () {
    for (var a = this.graphs, b = 0; b < a.length; b++) a[b].changeOpacity(1)
  },
  showGraph: function (a) {
    a.hidden = !1;
    this.initChart()
  },
  hideGraph: function (a) {
    a.hidden = !0;
    this.initChart()
  },
  hideGraphsBalloon: function (a) {
    a.showBalloon = !1;
    this.updateLegend()
  },
  showGraphsBalloon: function (a) {
    a.showBalloon = !0;
    this.updateLegend()
  },
  updateLegend: function () {
    this.legend && this.legend.invalidateSize()
  },
  animateAgain: function () {
    var a = this.graphs;
    if (a) for (var b = 0; b < a.length; b++) a[b].animationPlayed = !1
  }
}); AmCharts.AmRectangularChart = AmCharts.Class({
  inherits: AmCharts.AmCoordinateChart,
  construct: function () {
    AmCharts.AmRectangularChart.base.construct.call(this);
    this.createEvents('zoomed');
    this.marginRight = this.marginBottom = this.marginTop = this.marginLeft = 20;
    this.verticalPosition = this.horizontalPosition = this.depth3D = this.angle = 0;
    this.heightMultiplyer = this.widthMultiplyer = 1;
    this.zoomOutText = 'Show all';
    this.zoomOutButtonSet;
    this.zoomOutButton = {
      backgroundColor: '#b2e1ff',
      backgroundAlpha: 1
    };
    this.trendLines = [
    ];
    this.autoMargins = !0;
    this.marginsUpdated = !1;
    this.autoMarginOffset = 10
  },
  initChart: function () {
    AmCharts.AmRectangularChart.base.initChart.call(this);
    this.updateDxy();
    !this.marginsUpdated && this.autoMargins && this.resetMargins();
    this.updateMargins();
    this.updatePlotArea();
    this.updateScrollbars();
    this.updateTrendLines();
    this.updateChartCursor();
    this.updateValueAxes();
    this.scrollbarOnly || this.updateGraphs()
  },
  drawChart: function () {
    AmCharts.AmRectangularChart.base.drawChart.call(this);
    this.drawPlotArea();
    var a =
    this.chartData;
    a && 0 < a.length && ((a = this.chartCursor) && a.draw(), a = this.zoomOutText, '' != a && a && this.drawZoomOutButton())
  },
  resetMargins: function () {
    var a = {
    };
    if ('serial' == this.chartType) {
      for (var b = this.valueAxes, c = 0; c < b.length; c++) {
        var d = b[c];
        d.ignoreAxisWidth || (d.setOrientation(this.rotate), d.fixAxisPosition(), a[d.position] = !0)
      }
      if ((c = this.categoryAxis) && !c.ignoreAxisWidth) c.setOrientation(!this.rotate),
      c.fixAxisPosition(),
      c.fixAxisPosition(),
      a[c.position] = !0
    } else {
      d = this.xAxes;
      b = this.yAxes;
      for (c = 0; c < d.length; c++) {
        var e =
        d[c];
        e.ignoreAxisWidth || (e.setOrientation(!0), e.fixAxisPosition(), a[e.position] = !0)
      }
      for (c = 0; c < b.length; c++) d = b[c],
      d.ignoreAxisWidth || (d.setOrientation(!1), d.fixAxisPosition(), a[d.position] = !0)
    }
    a.left && (this.marginLeft = 0);
    a.right && (this.marginRight = 0);
    a.top && (this.marginTop = 0);
    a.bottom && (this.marginBottom = 0);
    this.fixMargins = a
  },
  measureMargins: function () {
    var a = this.valueAxes,
    b,
    c = this.autoMarginOffset,
    d = this.fixMargins,
    e = this.realWidth,
    f = this.realHeight,
    g = c,
    h = c,
    j = e - c;
    b = f - c;
    for (var l = 0; l < a.length; l++) b =
    this.getAxisBounds(a[l], g, j, h, b),
    g = b.l,
    j = b.r,
    h = b.t,
    b = b.b;
    if (a = this.categoryAxis) b = this.getAxisBounds(a, g, j, h, b),
    g = b.l,
    j = b.r,
    h = b.t,
    b = b.b;
    d.left && g < c && (this.marginLeft = Math.round( - g + c));
    d.right && j > e - c && (this.marginRight = Math.round(j - e + c));
    d.top && h < c && (this.marginTop = Math.round(this.marginTop - h + c + this.titleHeight));
    d.bottom && b > f - c && (this.marginBottom = Math.round(b - f + c));
    this.animateAgain();
    this.initChart()
  },
  getAxisBounds: function (a, b, c, d, e) {
    if (!a.ignoreAxisWidth) {
      var f = a.set;
      if (f) switch (f = f.getBBox(), a.position) {
        case 'top':
          a = f.y;
          d > a && (d = a);
          break;
        case 'bottom':
          a = f.y + f.height;
          e < a && (e = a);
          break;
        case 'right':
          a = f.x + f.width;
          c < a && (c = a);
          break;
        case 'left':
          a = f.x,
          b > a && (b = a)
      }
    }
    return {
      l: b,
      t: d,
      r: c,
      b: e
    }
  },
  drawZoomOutButton: function () {
    var a = this,
    b = a.container.set(),
    c = a.color,
    d = a.fontSize,
    e = a.zoomOutButton;
    e && (e.fontSize && (d = e.fontSize), e.color && (c = e.color));
    d = AmCharts.text(a.container, 29, 8, a.zoomOutText, {
      fill: c,
      'font-family': a.fontFamily,
      'font-size': d,
      'text-anchor': 'start'
    });
    c = d.getBBox();
    d.translate('0,' + c.height /
    2);
    e = AmCharts.rect(a.container, c.width + 40, c.height + 15, [
      e.backgroundColor
    ], [
      e.backgroundAlpha
    ]);
    b.push(e);
    d.toFront();
    e.hide();
    a.zoomOutButtonBG = e;
    b.push(d);
    a.set.push(b);
    d = b.getBBox();
    e = a.marginTopReal;
    d = a.marginLeftReal + a.plotAreaWidth - d.width;
    b.translate(d, e);
    b.hide();
    if (void 0 != a.pathToImages) {
      var f = a.container.image(a.pathToImages + 'lens.png', 0, 0, 17, 17);
      f.translate(d + 7, e + (c.height / 2 + 1));
      f.toFront();
      a.lens = f
    }
    a.touchEventsEnabled && b.touchstart(function () {
      a.rollOverZB()
    }).touchend(function () {
      a.clickZB()
    });
    b.mouseover(function () {
      a.rollOverZB()
    }).mouseout(function () {
      a.rollOutZB()
    }).click(function () {
      a.clickZB()
    });
    for (c = 0; c < b.length; c++) b[c].attr({
      cursor: 'pointer'
    });
    a.zoomOutButtonSet = b
  },
  rollOverZB: function () {
    this.zoomOutButtonBG.show()
  },
  rollOutZB: function () {
    this.zoomOutButtonBG.hide()
  },
  clickZB: function () {
    this.zoomOut()
  },
  zoomOut: function () {
    this.updateScrollbar = !0;
    this.zoom()
  },
  drawPlotArea: function () {
    var a = this.dx,
    b = this.dy,
    c = this.marginLeftReal,
    d = this.marginTopReal,
    e = this.plotAreaWidth,
    f = this.plotAreaHeight,
    g = AmCharts.toSvgColor(this.plotAreaFillColors),
    h = this.plotAreaFillAlphas;
    'object' == typeof h && (h = h[0]);
    g = AmCharts.rect(this.container, e, f, this.plotAreaFillColors, h, 1, this.plotAreaBorderColor, this.plotAreaBorderAlpha);
    g.translate(c + ',' + d);
    this.set.push(g);
    0 != a && 0 != b && (g.translate(a + ',' + b), g = this.plotAreaFillColors, 'object' == typeof g && (g = g[0]), g = AmCharts.adjustLuminosity(g, - 0.15), h = {
      fill: g,
      'fill-opacity': h,
      stroke: this.plotAreaBorderColor,
      'stroke-opacity': this.plotAreaBorderAlpha
    }, e = this.container.path(['M',
    0,
    0,
    'L',
    a,
    b,
    'L',
    e + a,
    b,
    'L',
    e,
    0,
    'L',
    0,
    0,
    'Z']).attr(h), e.translate(c + ',' + (d + f)), this.set.push(e), a = this.container.path(['M',
    0,
    0,
    'L',
    0,
    f,
    'L',
    a,
    f + b,
    'L',
    a,
    b,
    'L',
    0,
    0,
    'Z']).attr(h), a.translate(c + ',' + d), this.set.push(a))
  },
  updatePlotArea: function () {
    this.realWidth = this.updateWidth() - 1;
    this.realHeight = this.updateHeight() - 1;
    var a = this.realWidth - this.marginLeftReal - this.marginRightReal - this.dx,
    b = this.realHeight - this.marginTopReal - this.marginBottomReal;
    1 > a && (a = 1);
    1 > b && (b = 1);
    this.plotAreaWidth = Math.round(a);
    this.plotAreaHeight =
    Math.round(b)
  },
  updateDxy: function () {
    this.dx = this.depth3D * Math.cos(this.angle * Math.PI / 180);
    this.dy = - this.depth3D * Math.sin(this.angle * Math.PI / 180)
  },
  updateMargins: function () {
    var a = this.getTitleHeight();
    this.titleHeight = a;
    this.marginTopReal = this.marginTop - this.dy + a;
    this.marginBottomReal = this.marginBottom;
    this.marginLeftReal = this.marginLeft;
    this.marginRightReal = this.marginRight
  },
  updateValueAxes: function () {
    for (var a = this.valueAxes, b = 0; b < a.length; b++) {
      var c = a[b];
      c.axisRenderer = AmCharts.RectangularAxisRenderer;
      c.guideFillRenderer = AmCharts.RectangularAxisGuideFillRenderer;
      c.axisItemRenderer = AmCharts.RectangularAxisItemRenderer;
      c.dx = this.dx;
      c.dy = this.dy;
      c.visibleAxisWidth = this.plotAreaWidth;
      c.visibleAxisHeight = this.plotAreaHeight;
      c.visibleAxisX = this.marginLeftReal;
      c.visibleAxisY = this.marginTopReal;
      this.updateObjectSize(c)
    }
  },
  updateObjectSize: function (a) {
    a.width = this.plotAreaWidth * this.widthMultiplyer;
    a.height = this.plotAreaHeight * this.heightMultiplyer;
    a.x = this.marginLeftReal + this.horizontalPosition;
    a.y = this.marginTopReal +
    this.verticalPosition
  },
  updateGraphs: function () {
    for (var a = this.graphs, b = 0; b < a.length; b++) {
      var c = a[b];
      c.x = this.marginLeftReal + this.horizontalPosition;
      c.y = this.marginTopReal + this.verticalPosition;
      c.width = this.plotAreaWidth * this.widthMultiplyer;
      c.height = this.plotAreaHeight * this.heightMultiplyer;
      c.index = b;
      c.dx = this.dx;
      c.dy = this.dy;
      c.rotate = this.rotate;
      c.chartType = this.chartType
    }
  },
  updateChartCursor: function () {
    var a = this.chartCursor;
    a && (a.x = this.marginLeftReal, a.y = this.marginTopReal, a.width = this.plotAreaWidth, a.height = this.plotAreaHeight, a.chart = this)
  },
  updateScrollbars: function () {
  },
  addChartCursor: function (a) {
    AmCharts.callMethod('destroy', [
      this.chartCursor
    ]);
    a && (this.listenTo(a, 'changed', this.handleCursorChange), this.listenTo(a, 'zoomed', this.handleCursorZoom));
    this.chartCursor = a
  },
  removeChartCursor: function () {
    AmCharts.callMethod('destroy', [
      this.chartCursor
    ]);
    this.chartCursor = null
  },
  addTrendLine: function (a) {
    this.trendLines.push(a)
  },
  removeTrendLine: function (a) {
    for (var b = this.trendLines, c = b.length - 1; 0 <= c; c--) b[c] ==
    a && b.splice(c, 1)
  },
  adjustMargins: function (a, b) {
    var c = a.scrollbarHeight;
    'top' == a.position ? b ? this.marginLeftReal += c : this.marginTopReal += c : b ? this.marginRightReal += c : this.marginBottomReal += c
  },
  getScrollbarPosition: function (a, b, c) {
    a.position = b ? 'bottom' == c || 'left' == c ? 'bottom' : 'top' : 'top' == c || 'right' == c ? 'bottom' : 'top'
  },
  updateChartScrollbar: function (a, b) {
    if (a) {
      a.rotate = b;
      var c = this.marginTopReal,
      d = this.marginLeftReal,
      e = a.scrollbarHeight,
      f = this.dx,
      g = this.dy;
      'top' == a.position ? b ? (a.y = c, a.x = d - e)  : (a.y = c - e + g, a.x =
      d + f)  : b ? (a.y = c + g, a.x = d + this.plotAreaWidth + f)  : (a.y = c + this.plotAreaHeight + 1, a.x = this.marginLeftReal)
    }
  },
  showZoomOutButton: function () {
    var a = this.zoomOutButtonSet;
    a && (a.show(), this.zoomOutButtonBG.hide());
    this.lens && this.lens.show()
  },
  hideZoomOutButton: function () {
    var a = this.zoomOutButtonSet;
    a && (a.hide(), this.zoomOutButtonBG.hide());
    this.lens && this.lens.hide()
  },
  handleReleaseOutside: function (a) {
    AmCharts.AmRectangularChart.base.handleReleaseOutside.call(this, a);
    (a = this.chartCursor) && a.handleReleaseOutside()
  },
  handleMouseDown: function (a) {
    AmCharts.AmRectangularChart.base.handleMouseDown.call(this, a);
    var b = this.chartCursor;
    b && b.handleMouseDown(a)
  },
  handleCursorChange: function () {
  }
}); AmCharts.TrendLine = AmCharts.Class({
  construct: function () {
    this.createEvents('click');
    this.isProtected = !1;
    this.dashLength = 0;
    this.lineColor = '#00CC00';
    this.lineThickness = this.lineAlpha = 1
  },
  draw: function () {
    var a = this;
    a.destroy();
    var b = a.chart,
    c = b.container;
    a.set = c.set();
    var d,
    e,
    f,
    g,
    h = a.categoryAxis,
    j = a.initialDate,
    l = a.initialCategory,
    k = a.finalDate,
    n = a.finalCategory,
    o = a.valueAxis,
    m = a.valueAxisX,
    p = a.initialXValue,
    s = a.finalXValue,
    q = a.initialValue,
    r = a.finalValue;
    h && (j && (d = h.dateToCoordinate(j)), l && (d = h.categoryToCoordinate(l)), k && (e = h.dateToCoordinate(k)), n && (e = h.categoryToCoordinate(n)));
    m && !o.recalculateToPercents && (isNaN(p) || (d = m.getCoordinate(p)), isNaN(s) || (e = m.getCoordinate(s)));
    o && !o.recalculateToPercents && (isNaN(q) || (f = o.getCoordinate(q)), isNaN(r) || (g = o.getCoordinate(r)));
    !isNaN(d) && !isNaN(e) && !isNaN(f) && !isNaN(f) && (b.rotate ? (b = AmCharts.line(c, [
      f,
      g
    ], [
      d,
      e
    ], a.lineColor, a.lineAlpha, a.lineThickness, a.dashLength), b.translate(0, a.y))  : (b = AmCharts.line(c, [
      d,
      e
    ], [
      f,
      g
    ], a.lineColor, a.lineAlpha, a.lineThickness, a.dashLength), b.translate(a.x, 0)), a.line = b, a.set.push(b), hoverLine = AmCharts.line(c, [
      d,
      e
    ], [
      f,
      g
    ], a.lineColor, 0.01, 5), a.set.push(hoverLine), hoverLine.mouseup(function () {
      a.handleLineClick()
    }).mouseover(function () {
      a.handleLineOver()
    }).mouseout(function () {
      a.handleLineOut()
    }))
  },
  handleLineClick: function () {
    var a = {
      type: 'click',
      trendLine: this
    };
    this.fire(a.type, a)
  },
  handleLineOver: function () {
    var a = this.rollOverColor;
    void 0 != a && this.line.attr({
      stroke: a
    })
  },
  handleLineOut: function () {
    this.line.attr({
      stroke: this.lineColor
    })
  },
  destroy: function () {
    AmCharts.removeSet(this.set)
  }
}); AmCharts.AmSerialChart = AmCharts.Class({
  inherits: AmCharts.AmRectangularChart,
  construct: function () {
    AmCharts.AmSerialChart.base.construct.call(this);
    this.createEvents('changed');
    this.columnSpacing = 5;
    this.columnWidth = 0.8;
    this.maxSelectedSeries;
    this.updateScrollbar = !0;
    this.maxSelectedTime;
    this.categoryAxis = new AmCharts.CategoryAxis;
    this.categoryAxis.chart = this;
    this.chartType = 'serial';
    this.zoomOutOnDataUpdate = !0;
    this.skipZoom = !1
  },
  initChart: function () {
    AmCharts.AmSerialChart.base.initChart.call(this);
    this.updateCategoryAxis();
    this.dataChanged && (this.updateData(), this.dataChanged = !1, this.dispatchDataUpdated = !0);
    this.updateScrollbar = !0;
    this.drawChart();
    this.autoMargins && !this.marginsUpdated && (this.marginsUpdated = !0, this.measureMargins())
  },
  validateData: function (a) {
    this.marginsUpdated = !1;
    this.zoomOutOnDataUpdate && !a && (this.endTime = this.end = this.startTime = this.start = NaN);
    AmCharts.AmSerialChart.base.validateData.call(this)
  },
  drawChart: function () {
    AmCharts.AmSerialChart.base.drawChart.call(this);
    var a = this.chartData;
    if (a) if (0 <
    a.length) {
      var b = this.chartScrollbar;
      b && b.draw();
      var b = a.length - 1,
      c,
      d;
      c = this.categoryAxis;
      if (c.parseDates && !c.equalSpacing) {
        if (c = this.startTime, d = this.endTime, isNaN(c) || isNaN(d)) c = a[0].time,
        d = a[b].time
      } else if (c = this.start, d = this.end, isNaN(c) || isNaN(d)) c = 0,
      d = b;
      this.endTime = this.startTime = this.end = this.start = void 0;
      this.zoom(c, d)
    } else this.cleanChart();
    this.bringLabelsToFront();
    this.chartCreated = !0;
    this.dispatchDataUpdatedEvent()
  },
  cleanChart: function () {
    AmCharts.callMethod('destroy', [
      this.valueAxes,
      this.graphs,
      this.categoryAxis,
      this.chartScrollbar,
      this.chartCursor
    ])
  },
  updateCategoryAxis: function () {
    var a = this.categoryAxis;
    a.id = 'categoryAxis';
    a.rotate = this.rotate;
    a.axisRenderer = AmCharts.RectangularAxisRenderer;
    a.guideFillRenderer = AmCharts.RectangularAxisGuideFillRenderer;
    a.axisItemRenderer = AmCharts.RectangularAxisItemRenderer;
    a.setOrientation(!this.rotate);
    a.x = this.marginLeftReal;
    a.y = this.marginTopReal;
    a.dx = this.dx;
    a.dy = this.dy;
    a.width = this.plotAreaWidth;
    a.height = this.plotAreaHeight;
    a.visibleAxisWidth =
    this.plotAreaWidth;
    a.visibleAxisHeight = this.plotAreaHeight;
    a.visibleAxisX = this.marginLeftReal;
    a.visibleAxisY = this.marginTopReal
  },
  updateValueAxes: function () {
    AmCharts.AmSerialChart.base.updateValueAxes.call(this);
    for (var a = this.valueAxes, b = 0; b < a.length; b++) {
      var c = a[b];
      c.rotate = this.rotate;
      c.setOrientation(this.rotate);
      var d = this.categoryAxis;
      if (!d.startOnAxis || d.parseDates) c.expandMinMax = !0
    }
  },
  updateData: function () {
    this.parseData();
    this.columnCount = this.countColumns();
    this.chartCursor && this.chartCursor.updateData();
    for (var a = this.graphs, b = 0; b < a.length; b++) {
      var c = a[b];
      c.columnCount = this.columnCount;
      c.data = this.chartData
    }
  },
  updateMargins: function () {
    AmCharts.AmSerialChart.base.updateMargins.call(this);
    var a = this.chartScrollbar;
    a && (this.getScrollbarPosition(a, this.rotate, this.categoryAxis.position), this.adjustMargins(a, this.rotate))
  },
  updateScrollbars: function () {
    this.updateChartScrollbar(this.chartScrollbar, this.rotate)
  },
  zoom: function (a, b) {
    var c = this.categoryAxis;
    c.parseDates && !c.equalSpacing ? this.timeZoom(a, b)  : this.indexZoom(a, b);
    this.updateDepths()
  },
  timeZoom: function (a, b) {
    var c = this.maxSelectedTime;
    isNaN(c) || (b != this.endTime && b - a > c && (a = b - c, this.updateScrollbar = !0), a != this.startTime && b - a > c && (b = a + c, this.updateScrollbar = !0));
    var d = this.chartData,
    e = this.categoryAxis;
    if (d && 0 < d.length && (a != this.startTime || b != this.endTime)) {
      var f = e.minDuration(),
      g = d[0].time;
      this.firstTime = g;
      var h = d[d.length - 1].time;
      this.lastTime = h;
      a || (a = g, isNaN(c) || (a = h - c));
      b || (b = h);
      a > h && (a = h);
      b < g && (b = g);
      a < g && (a = g);
      b > h && (b = h);
      b < a && (b = a + f);
      this.startTime = a;
      this.endTime =
      b;
      c = d.length - 1;
      f = this.getClosestIndex(d, 'time', a, !0, 0, c);
      d = this.getClosestIndex(d, 'time', b, !1, f, c);
      e.timeZoom(a, b);
      e.zoom(f, d);
      this.start = AmCharts.fitToBounds(f, 0, c);
      this.end = AmCharts.fitToBounds(d, 0, c);
      this.zoomAxesAndGraphs();
      this.zoomScrollbar();
      a != g || b != h ? this.showZoomOutButton()  : this.hideZoomOutButton();
      this.dispatchTimeZoomEvent()
    }
    this.renderfix()
  },
  indexZoom: function (a, b) {
    var c = this.maxSelectedSeries;
    isNaN(c) || (b != this.end && b - a > c && (a = b - c, this.updateScrollbar = !0), a != this.start && b - a > c && (b = a + c, this.updateScrollbar = !0));
    if (a != this.start || b != this.end) {
      var d = this.chartData.length - 1;
      isNaN(a) && (a = 0, isNaN(c) || (a = d - c));
      isNaN(b) && (b = d);
      b < a && (b = a);
      b > d && (b = d);
      a > d && (a = d - 1);
      0 > a && (a = 0);
      this.start = a;
      this.end = b;
      this.categoryAxis.zoom(a, b);
      this.zoomAxesAndGraphs();
      this.zoomScrollbar();
      0 != a || b != this.chartData.length - 1 ? this.showZoomOutButton()  : this.hideZoomOutButton();
      this.dispatchIndexZoomEvent()
    }
    this.renderfix()
  },
  updateGraphs: function () {
    AmCharts.AmSerialChart.base.updateGraphs.call(this);
    for (var a = this.graphs, b = 0; b < a.length; b++) {
      var c = a[b];
      c.columnWidth = this.columnWidth;
      c.categoryAxis = this.categoryAxis
    }
  },
  updateDepths: function () {
    var a = this.container.rect(0, 0, 10, 10);
    this.mostFrontObj = a;
    this.updateColumnsDepth();
    for (var b = this.graphs, c = 0; c < b.length; c++) {
      var d = b[c];
      'column' != d.type && d.set.insertBefore(a);
      var e = d.allBullets;
      if (e) for (var f = 0; f < e.length; f++) {
        var g = e[f];
        g && g.node && g.insertBefore(a)
      }
      if (e = d.positiveObjectsToClip) for (f = 0; f < e.length; f++) d.setPositiveClipRect(e[f]);
      if (e = d.negativeObjectsToClip) for (f =
      0; f < e.length; f++) d.setNegativeClipRect(e[f]);
      if (e = d.objectsToAddListeners) for (f = 0; f < e.length; f++) d.addClickListeners(e[f]),
      d.addHoverListeners(e[f])
    }
    this.updateTrendLinesDepth();
    (c = this.chartCursor) && c.set.insertBefore(a);
    (c = this.zoomOutButtonSet) && c.insertBefore(a);
    b = this.valueAxes;
    for (c = 0; c < b.length; c++) {
      f = b[c];
      f.axisLine.set.toFront();
      f.grid0 && AmCharts.putSetToFront(f.grid0);
      AmCharts.putSetToFront(f.axisLine.set);
      d = f.allLabels;
      for (f = 0; f < d.length; f++) (e = d[f]) && e.toFront()
    }
    c = this.categoryAxis;
    c.axisLine.set.toFront();
    d = c.allLabels;
    for (f = 0; f < d.length; f++) (e = c.allLabels[f]) && e.toFront();
    a.remove();
    this.bgImg && this.bgImg.toBack();
    this.background && this.background.toBack();
    this.drb()
  },
  updateTrendLinesDepth: function () {
    var a = this.trendLines;
    for (i = 0; i < a.length; i++) {
      var b = a[i],
      c = b.set;
      c && c.insertBefore(this.mostFrontObj);
      if (c = b.line) {
        var b = b.valueAxis,
        d,
        e;
        this.rotate ? (d = b.visibleAxisX, e = 0)  : (e = b.visibleAxisY, d = 0);
        c.attr({
          'clip-rect': d + ',' + e + ',' + b.visibleAxisWidth + ',' + b.visibleAxisHeight
        })
      }
    }
  },
  updateColumnsDepth: function () {
    var a,
    b = this.graphs;
    this.columnsArray = [
    ];
    for (a = 0; a < b.length; a++) {
      var c = b[a].columnsArray;
      if (c) for (var d = 0; d < c.length; d++) this.columnsArray.push(c[d])
    }
    this.columnsArray.sort(this.compareDepth);
    for (a = 0; a < this.columnsArray.length; a++) this.columnsArray[a].column.set.insertBefore(this.mostFrontObj)
  },
  compareDepth: function (a, b) {
    return a.depth > b.depth ? 1 : - 1
  },
  zoomScrollbar: function () {
    var a = this.chartScrollbar,
    b = this.categoryAxis;
    a && this.updateScrollbar && (b.parseDates && !b.equalSpacing ? a.timeZoom(this.startTime, this.endTime)  :
    a.zoom(this.start, this.end), this.updateScrollbar = !0)
  },
  updateTrendLines: function () {
    for (var a = this.trendLines, b = 0; b < a.length; b++) {
      var c = a[b];
      c.chart = this;
      c.valueAxis || (c.valueAxis = this.valueAxes[0]);
      c.categoryAxis = this.categoryAxis
    }
  },
  zoomAxesAndGraphs: function () {
    for (var a = this.valueAxes, b = 0; b < a.length; b++) a[b].zoom(this.start, this.end);
    a = this.graphs;
    for (b = 0; b < a.length; b++) a[b].zoom(this.start, this.end);
    a = this.trendLines;
    for (b = 0; b < a.length; b++) {
      var c = a[b];
      c.valueAxis.recalculateToPercents ? c.set && c.set.hide()  : (c.x = this.marginLeftReal + this.horizontalPosition, c.y = this.marginTopReal + this.verticalPosition, c.draw())
    }(b = this.chartCursor) && b.zoom(this.start, this.end, this.startTime, this.endTime)
  },
  countColumns: function () {
    for (var a = 0, b = this.valueAxes.length, c = this.graphs.length, d, e, f = !1, g, h = 0; h < b; h++) {
      e = this.valueAxes[h];
      var j = e.stackType;
      if ('100%' == j || 'regular' == j) {
        f = !1;
        for (g = 0; g < c; g++) d = this.graphs[g],
        !d.hidden && d.valueAxis == e && 'column' == d.type && (!f && d.stackable && (a++, f = !0), d.stackable || a++, d.columnIndex = a - 1)
      }
      if ('none' ==
      j || '3d' == j) for (g = 0; g < c; g++) d = this.graphs[g],
      !d.hidden && d.valueAxis == e && 'column' == d.type && (d.columnIndex = a, a++);
      if ('3d' == j) {
        for (h = 0; h < c; h++) d = this.graphs[h],
        d.depthCount = a;
        a = 1
      }
    }
    return a
  },
  parseData: function () {
    AmCharts.AmSerialChart.base.parseData.call(this);
    this.parseSerialData()
  },
  getCategoryIndexByValue: function (a) {
    for (var b = this.chartData, c, d = 0; d < b.length; d++) b[d].category == a && (c = d);
    return c
  },
  handleCursorChange: function (a) {
    this.updateLegendValues(a.index)
  },
  handleCursorZoom: function (a) {
    this.updateScrollbar =
    !0;
    this.zoom(a.start, a.end)
  },
  handleScrollbarZoom: function (a) {
    this.updateScrollbar = !1;
    this.zoom(a.start, a.end)
  },
  dispatchTimeZoomEvent: function () {
    if (this.prevStartTime != this.startTime || this.prevEndTime != this.endTime) {
      var a = {
        type: 'zoomed'
      };
      a.startDate = new Date(this.startTime);
      a.endDate = new Date(this.endTime);
      a.startIndex = this.start;
      a.endIndex = this.end;
      this.startIndex = this.start;
      this.endIndex = this.end;
      this.prevStartTime = this.startTime;
      this.prevEndTime = this.endTime;
      var b = this.categoryAxis,
      c = AmCharts.extractPeriod(b.minPeriod).period,
      b = b.dateFormatsObject[c];
      a.startValue = AmCharts.formatDate(a.startDate, b);
      a.endValue = AmCharts.formatDate(a.endDate, b);
      this.fire(a.type, a)
    }
  },
  dispatchIndexZoomEvent: function () {
    if (this.prevStartIndex != this.start || this.prevEndIndex != this.end) {
      this.startIndex = this.start;
      this.endIndex = this.end;
      var a = this.chartData;
      if (a && 0 < a.length && !isNaN(this.start) && !isNaN(this.end)) {
        var b = {
          type: 'zoomed'
        };
        b.startIndex = this.start;
        b.endIndex = this.end;
        b.startValue = a[this.start].category;
        b.endValue = a[this.end].category;
        this.categoryAxis.parseDates && (this.startTime = a[this.start].time, this.endTime = a[this.end].time, b.startDate = new Date(this.startTime), b.endDate = new Date(this.endTime));
        this.prevStartIndex = this.start;
        this.prevEndIndex = this.end;
        this.fire(b.type, b)
      }
    }
  },
  updateLegendValues: function (a) {
    for (var b = this.graphs, c = 0; c < b.length; c++) {
      var d = b[c];
      d.currentDataItem = isNaN(a) ? void 0 : this.chartData[a].axes[d.valueAxis.id].graphs[d.id]
    }
    this.legend && this.legend.updateValues()
  },
  getClosestIndex: function (a, b, c, d, e, f) {
    0 > e && (e = 0);
    f > a.length - 1 && (f = a.length -
    1);
    var g = e + Math.round((f - e) / 2),
    h = a[g][b];
    if (1 >= f - e) {
      if (d) return e;
      d = a[f][b];
      return Math.abs(a[e][b] - c) < Math.abs(d - c) ? e : f
    }
    return c == h ? g : c < h ? this.getClosestIndex(a, b, c, d, e, g)  : this.getClosestIndex(a, b, c, d, g, f)
  },
  zoomToIndexes: function (a, b) {
    this.updateScrollbar = !0;
    var c = this.chartData;
    if (c) {
      var d = c.length;
      0 < d && (0 > a && (a = 0), b > d - 1 && (b = d - 1), d = this.categoryAxis, d.parseDates && !d.equalSpacing ? this.zoom(c[a].time, c[b].time)  : this.zoom(a, b))
    }
  },
  zoomToDates: function (a, b) {
    this.updateScrollbar = !0;
    var c = this.chartData;
    if (this.categoryAxis.equalSpacing) {
      var d = this.getClosestIndex(c, 'time', a.getTime(), !0, 0, c.length),
      c = this.getClosestIndex(c, 'time', b.getTime(), !1, 0, c.length);
      this.zoom(d, c)
    } else this.zoom(a.getTime(), b.getTime())
  },
  zoomToCategoryValues: function (a, b) {
    this.updateScrollbar = !0;
    this.zoom(this.getCategoryIndexByValue(a), this.getCategoryIndexByValue(b))
  },
  formatString: function (a, b) {
    var c = b.graph;
    if ( - 1 != a.indexOf('[[category]]')) {
      var d = b.serialDataItem.category;
      if (this.categoryAxis.parseDates) {
        var e = this.balloonDateFormat,
        f = this.chartCursor;
        f && (e = f.categoryBalloonDateFormat);
        - 1 != a.indexOf('[[category]]') && (e = AmCharts.formatDate(d, e), - 1 != e.indexOf('fff') && (e = AmCharts.formatMilliseconds(e, d)), d = e)
      }
      a = a.replace(/\[\[category\]\]/g, '' + d)
    }
    c = c.numberFormatter;
    c || (c = this.numberFormatter);
    d = b.graph.valueAxis;
    if ((e = d.duration) && !isNaN(b.values.value)) d = AmCharts.formatDuration(b.values.value, e, '', d.durationUnits, d.maxInterval, c),
    a = a.replace(RegExp('\\[\\[value\\]\\]', 'g'), d);
    d = [
      'value',
      'open',
      'low',
      'high',
      'close'
    ];
    e = this.percentFormatter;
    a = AmCharts.formatValue(a, b.percents, d, e, 'percents.');
    a = AmCharts.formatValue(a, b.values, d, c, '', this.usePrefixes, this.prefixesOfSmallNumbers, this.prefixesOfBigNumbers);
    a = AmCharts.formatValue(a, b.values, [
      'percents'
    ], e);
    - 1 != a.indexOf('[[') && (a = AmCharts.formatDataContextValue(a, b.dataContext));
    return a = AmCharts.AmSerialChart.base.formatString.call(this, a, b)
  },
  addChartScrollbar: function (a) {
    AmCharts.callMethod('destroy', [
      this.chartScrollbar
    ]);
    a && (a.chart = this, this.listenTo(a, 'zoomed', this.handleScrollbarZoom));
    this.rotate ? void 0 == a.width && (a.width = a.scrollbarHeight)  : void 0 == a.height && (a.height = a.scrollbarHeight);
    this.chartScrollbar = a
  },
  removeChartScrollbar: function () {
    AmCharts.callMethod('destroy', [
      this.chartScrollbar
    ]);
    this.chartScrollbar = null
  },
  handleReleaseOutside: function (a) {
    AmCharts.AmSerialChart.base.handleReleaseOutside.call(this, a);
    AmCharts.callMethod('handleReleaseOutside', [
      this.chartScrollbar
    ])
  }
}); AmCharts.AmRadarChart = AmCharts.Class({
  inherits: AmCharts.AmCoordinateChart,
  construct: function () {
    AmCharts.AmRadarChart.base.construct.call(this);
    this.marginRight = this.marginBottom = this.marginTop = this.marginLeft = 0;
    this.chartType = 'radar';
    this.radius = '35%'
  },
  initChart: function () {
    AmCharts.AmRadarChart.base.initChart.call(this);
    this.dataChanged && (this.updateData(), this.dataChanged = !1, this.dispatchDataUpdated = !0);
    this.drawChart()
  },
  updateData: function () {
    this.parseData();
    for (var a = this.graphs, b = 0; b < a.length; b++) a[b].data =
    this.chartData
  },
  updateGraphs: function () {
    for (var a = this.graphs, b = 0; b < a.length; b++) {
      var c = a[b];
      c.index = b;
      c.width = this.realRadius;
      c.height = this.realRadius;
      c.x = this.centerX;
      c.y = this.centerY;
      c.chartType = this.chartType
    }
  },
  parseData: function () {
    AmCharts.AmRadarChart.base.parseData.call(this);
    this.parseSerialData()
  },
  updateValueAxes: function () {
    for (var a = this.valueAxes, b = 0; b < a.length; b++) {
      var c = a[b];
      c.axisRenderer = AmCharts.RadarAxisRenderer;
      c.guideFillRenderer = AmCharts.RadarAxisGuideFillRenderer;
      c.axisItemRenderer =
      AmCharts.RadarAxisItemRenderer;
      c.autoGridCount = !1;
      c.x = this.centerX;
      c.y = this.centerY;
      c.width = this.realRadius;
      c.height = this.realRadius
    }
  },
  drawChart: function () {
    AmCharts.AmRadarChart.base.drawChart.call(this);
    var a = this.updateWidth(),
    b = this.updateHeight(),
    c = this.marginTop + this.getTitleHeight(),
    d = this.marginLeft,
    b = b - c - this.marginBottom;
    this.centerX = d + (a - d - this.marginRight) / 2;
    this.centerY = c + b / 2;
    this.realRadius = AmCharts.toCoordinate(this.radius, a, b);
    this.updateValueAxes();
    this.updateGraphs();
    if (a = this.chartData) if (0 <
    a.length) {
      for (c = 0; c < this.valueAxes.length; c++) this.valueAxes[c].zoom(0, a.length - 1);
      for (c = 0; c < this.graphs.length; c++) this.graphs[c].zoom(0, a.length - 1)
    } else this.cleanChart();
    this.bringLabelsToFront();
    this.chartCreated = !0;
    this.dispatchDataUpdatedEvent();
    this.drb()
  },
  formatString: function (a, b) {
    var c = b.graph;
    - 1 != a.indexOf('[[category]]') && (a = a.replace(/\[\[category\]\]/g, '' + b.serialDataItem.category));
    c = c.numberFormatter;
    c || (c = this.numberFormatter);
    a = AmCharts.formatValue(a, b.values, [
      'value'
    ], c, '', this.usePrefixes, this.prefixesOfSmallNumbers, this.prefixesOfBigNumbers);
    return a = AmCharts.AmRadarChart.base.formatString.call(this, a, b)
  },
  cleanChart: function () {
    this.callMethod('destroy', [
      this.valueAxes,
      this.graphs
    ])
  }
}); AmCharts.AxisBase = AmCharts.Class({
  construct: function () {
    this.dy = this.dx = 0;
    this.axisWidth;
    this.axisThickness = 1;
    this.axisColor = '#000000';
    this.axisAlpha = 1;
    this.gridCount = this.tickLength = 5;
    this.gridAlpha = 0.15;
    this.gridThickness = 1;
    this.gridColor = '#000000';
    this.dashLength = 0;
    this.labelFrequency = 1;
    this.showLastLabel = this.showFirstLabel = !0;
    this.fillColor = '#FFFFFF';
    this.fillAlpha = 0;
    this.labelsEnabled = !0;
    this.labelRotation = 0;
    this.autoGridCount = !0;
    this.valueRollOverColor = '#CC0000';
    this.offset = 0;
    this.guides = [
    ];
    this.visible = !0;
    this.counter = 0;
    this.guides = [
    ];
    this.ignoreAxisWidth = this.inside = !1;
    this.titleColor;
    this.titleFontSize;
    this.titleBold = !0
  },
  zoom: function (a, b) {
    this.start = a;
    this.end = b;
    this.dataChanged = !0;
    this.draw()
  },
  fixAxisPosition: function () {
    var a = this.position;
    'horizontal' == this.orientation ? ('left' == a && (a = 'bottom'), 'right' == a && (a = 'top'))  : ('bottom' == a && (a = 'left'), 'top' == a && (a = 'right'));
    this.position = a
  },
  draw: function () {
    var a = this.chart;
    void 0 == this.titleColor && (this.titleColor = a.color);
    isNaN(this.titleFontSize) && (this.titleFontSize = a.fontSize + 1);
    this.allLabels = [
    ];
    this.counter = 0;
    this.destroy();
    this.fixAxisPosition();
    this.set = this.chart.container.set();
    this.axisLine = new this.axisRenderer(this);
    a = this.axisLine.axisWidth;
    if (this.autoGridCount) {
      var b;
      'vertical' == this.orientation ? (b = this.height / 35, 3 > b && (b = 3))  : b = this.width / 75;
      this.gridCount = b
    }
    this.axisWidth = a;
    this.addTitle()
  },
  setOrientation: function (a) {
    this.orientation = a ? 'horizontal' : 'vertical'
  },
  addTitle: function () {
    var a = this.title;
    if (a) {
      var b = this.chart,
      a = AmCharts.text(b.container, 0, 0, a, {
        fill: this.titleColor,
        'font-family': b.fontFamily,
        'font-size': this.titleFontSize
      });
      !0 == this.titleBold && a.attr({
        'font-weight': 'bold'
      });
      this.set.push(a);
      this.titleLabel = a
    }
  },
  positionTitle: function () {
    var a = this.titleLabel;
    if (a) {
      var b,
      c;
      this.set.exclude(a);
      var d = this.set.getBBox();
      this.set.push(a);
      var e = d.x,
      f = d.y,
      g = d.width,
      d = d.height,
      h = this.visibleAxisX,
      j = this.visibleAxisY,
      l = this.visibleAxisWidth,
      k = this.visibleAxisHeight,
      n = 'middle',
      o = a.getBBox(),
      m = 0,
      p = this.titleFontSize / 2;
      switch (this.position) {
        case 'top':
          b =
          h + l / 2;
          c = f - 10 - p;
          break;
        case 'bottom':
          b = h + l / 2;
          c = f + d + 10 + p;
          break;
        case 'left':
          b = - o.width / 2 - (h - e);
          b -= 10 + p;
          c = j + k / 2;
          m = 270;
          n = 'start';
          break;
        case 'right':
          b = l - o.width / 2 - (h + l - (e + g)) + 10 + p,
          c = j + k / 2,
          m = 270,
          n = 'start'
      }
      a.attr({
        'text-anchor': n
      });
      'start' == n ? a.transform('...r' + m)  : a.transform('r' + m);
      a.transform('...T' + b + ',' + c)
    }
  },
  pushAxisItem: function (a) {
    a = a.graphics();
    0 < a.length && this.set.push(a)
  },
  addGuide: function (a) {
    this.guides.push(a)
  },
  removeGuide: function (a) {
    for (var b = this.guides, c = 0; c < b.length; c++) b[c] == a && b.splice(c, 1)
  },
  handleGuideOver: function (a) {
    clearTimeout(this.chart.hoverInt);
    var a = this.guides[a],
    b = a.graphics.getBBox(),
    c = b.x + b.width / 2,
    b = b.y + b.height / 2,
    d = a.fillColor;
    void 0 == d && (d = a.lineColor);
    this.chart.showBalloon(a.balloonText, d, !0, c, b)
  },
  handleGuideOut: function () {
    this.chart.hideBalloon()
  },
  destroy: function () {
    AmCharts.removeSet(this.set);
    this.axisLine && AmCharts.removeSet(this.axisLine.set)
  }
}); AmCharts.ValueAxis = AmCharts.Class({
  inherits: AmCharts.AxisBase,
  construct: function () {
    this.createEvents('axisChanged', 'logarithmicAxisFailed', 'axisSelfZoomed', 'axisZoomed');
    AmCharts.ValueAxis.base.construct.call(this);
    this.dataChanged = !0;
    this.gridCount = 8;
    this.stackType = 'none';
    this.position = 'left';
    this.unitPosition = 'right';
    this.recalculateToPercents = this.includeHidden = this.includeGuidesInMinMax = this.integersOnly = !1;
    this.duration;
    this.durationUnits = {
      DD: 'd. ',
      hh: ':',
      mm: ':',
      ss: ''
    };
    this.scrollbar = !1;
    this.maxDecCount;
    this.baseValue = 0;
    this.radarCategoriesEnabled = !0;
    this.gridType = 'polygons';
    this.useScientificNotation = !1;
    this.axisTitleOffset = 10
  },
  updateData: function () {
    0 >= this.gridCount && (this.gridCount = 1);
    this.data = this.chart.chartData;
    'xy' != this.chart.chartType && (this.stackGraphs('smoothedLine'), this.stackGraphs('line'), this.stackGraphs('column'), this.stackGraphs('step'));
    this.recalculateToPercents && this.recalculate();
    this.synchronizationMultiplyer && this.synchronizeWithAxis ? this.foundGraphs = !0 : (this.foundGraphs =
    !1, this.getMinMax())
  },
  draw: function () {
    AmCharts.ValueAxis.base.draw.call(this);
    var a = this.chart,
    b = this.set;
    'duration' == this.type && (this.duration = 'ss');
    !0 == this.dataChanged && (this.updateData(), this.dataChanged = !1);
    if (this.logarithmic && (0 >= this.getMin(0, this.data.length - 1) || 0 >= this.minimum)) this.fire('logarithmicAxisFailed', {
      type: 'logarithmicAxisFailed'
    });
     else {
      this.grid0 = null;
      var c,
      d,
      e = a.dx,
      f = a.dy,
      g = !1,
      h = this.logarithmic,
      j = a.chartType;
      if (!isNaN(this.min) && !isNaN(this.max) && this.foundGraphs && Infinity !=
      this.min && - Infinity != this.max) {
        var l = this.labelFrequency,
        k = this.showFirstLabel,
        n = this.showLastLabel,
        o = 1,
        m = 0,
        p = Math.round((this.max - this.min) / this.step) + 1;
        if (!0 == h) {
          var s = Math.log(this.max) * Math.LOG10E - Math.log(this.minReal) * Math.LOG10E;
          this.stepWidth = this.axisWidth / s;
          2 < s && (p = Math.ceil(Math.log(this.max) * Math.LOG10E) + 1, m = Math.round(Math.log(this.minReal) * Math.LOG10E), p > this.gridCount && (o = Math.ceil(p / this.gridCount)))
        } else this.stepWidth = this.axisWidth / (this.max - this.min);
        c = 0;
        1 > this.step && - 1 < this.step && (d = this.step.toString(), c = - 1 != d.indexOf('e-') ? Number(d.split('-') [1])  : d.split('.') [1].length);
        this.integersOnly && (c = 0);
        c > this.maxDecCount && (c = this.maxDecCount);
        isNaN(this.precision) || (c = this.precision);
        this.max = AmCharts.roundTo(this.max, this.maxDecCount);
        this.min = AmCharts.roundTo(this.min, this.maxDecCount);
        var q = {
        };
        q.precision = c;
        q.decimalSeparator = a.numberFormatter.decimalSeparator;
        q.thousandsSeparator = a.numberFormatter.thousandsSeparator;
        this.numberFormatter = q;
        var r = this.guides,
        v = r.length;
        if (0 < v) {
          var y =
          this.fillAlpha;
          for (d = this.fillAlpha = 0; d < v; d++) {
            var B = r[d],
            J = NaN;
            if (!isNaN(B.toValue)) {
              var J = this.getCoordinate(B.toValue),
              t = new this.axisItemRenderer(this, J, '', !0, NaN, NaN, B);
              this.pushAxisItem(t)
            }
            var I = NaN;
            isNaN(B.value) || (I = this.getCoordinate(B.value), t = new this.axisItemRenderer(this, I, B.label, !0, NaN, (J - I) / 2, B), this.pushAxisItem(t));
            if (!isNaN(J - I)) {
              t = new this.guideFillRenderer(this, J - I, I, B);
              this.pushAxisItem(t);
              t = t.graphics();
              B.graphics = t;
              t.index = d;
              var H = this;
              B.balloonText && (t.mouseover(function () {
                H.handleGuideOver(this.index)
              }), t.mouseout(function () {
                H.handleGuideOut(this.index)
              }))
            }
          }
          this.fillAlpha = y
        }
        r = !1;
        for (d = m; d < p; d += o) t = AmCharts.roundTo(this.step * d + this.min, c),
        - 1 != ('' + t).indexOf('e') && (r = !0, ('' + t).split('e'));
        this.duration && (this.maxInterval = AmCharts.getMaxInterval(this.max, this.duration));
        for (d = m; d < p; d += o) if (m = this.step * d + this.min, m = AmCharts.roundTo(m, this.maxDecCount + 1), !(this.integersOnly && Math.round(m) != m)) {
          !0 == h && (0 == m && (m = this.minReal), 2 < s && (m = Math.pow(10, d)), r = - 1 != ('' + m).indexOf('e') ? !0 : !1);
          this.useScientificNotation && (r = !0);
          this.usePrefixes && (r = !1);
          r ? (t = - 1 == ('' + m).indexOf('e') ? m.toExponential(15)  : '' + m, t = t.split('e'), c = Number(t[0]), t = Number(t[1]), 10 == c && (c = 1, t += 1), t = c + 'e' + t, 0 == m && (t = '0'), 1 == m && (t = '1'))  : (h && (c = ('' + m).split('.'), q.precision = c[1] ? c[1].length : - 1), t = this.usePrefixes ? AmCharts.addPrefix(m, a.prefixesOfBigNumbers, a.prefixesOfSmallNumbers, q)  : AmCharts.formatNumber(m, q, q.precision));
          this.duration && (t = AmCharts.formatDuration(m, this.duration, '', this.durationUnits, this.maxInterval, q));
          this.recalculateToPercents ?
          t += '%' : (c = this.unit) && (t = 'left' == this.unitPosition ? c + t : t + c);
          Math.round(d / l) != d / l && (t = void 0);
          if (0 == d && !k || d == p - 1 && !n) t = ' ';
          c = this.getCoordinate(m);
          t = new this.axisItemRenderer(this, c, t);
          this.pushAxisItem(t);
          if (m == this.baseValue && 'radar' != j) {
            var D,
            T,
            m = this.visibleAxisWidth,
            t = this.visibleAxisHeight,
            v = this.visibleAxisX,
            y = this.visibleAxisY;
            'horizontal' == this.orientation ? c >= v && c <= v + m + 1 && (D = [
              c,
              c,
              c + e
            ], T = [
              0 + t,
              0,
              f
            ])  : c >= y && c <= y + t + 1 && (D = [
              0,
              m,
              m + e
            ], T = [
              c,
              c,
              c + f
            ]);
            D && (this.grid0 = AmCharts.line(a.container, D, T, this.gridColor, 2 * this.gridAlpha, 1, this.dashLength), b.push(this.grid0))
          }
        }
        e = this.baseValue;
        this.min > this.baseValue && this.max > this.baseValue && (e = this.min);
        this.min < this.baseValue && this.max < this.baseValue && (e = this.max);
        h && e < this.minReal && (e = this.minReal);
        this.baseCoord = this.getCoordinate(e);
        e = {
          type: 'axisChanged'
        };
        e.min = h ? this.minReal : this.min;
        e.max = this.max;
        this.fire('axisChanged', e);
        this.axisCreated = !0
      } else g = !0;
      'radar' != j ? this.rotate ? b.translate('0,' + a.marginTopReal)  : b.translate(a.marginLeftReal + ',0')  : this.axisLine.set.toFront();
      this.positionTitle();
      !this.visible || g ? (b.hide(), this.axisLine.set.hide())  : (b.show(), this.axisLine.set.show())
    }
  },
  stackGraphs: function (a) {
    var b = this.stackType;
    'stacked' == b && (b = 'regular');
    'line' == b && (b = 'none');
    '100% stacked' == b && (b = '100%');
    this.stackType = b;
    var c = [
    ],
    d = [
    ],
    e = [
    ],
    f = [
    ],
    g,
    h = this.chart.graphs,
    j,
    l,
    k;
    if (('line' == a || 'step' == a || 'smoothedLine' == a) && ('regular' == b || '100%' == b)) for (k = 0; k < h.length; k++) g = h[k],
    g.hidden || (l = g.type, g.chart == this.chart && g.valueAxis == this && a == l && g.stackable && (j && (g.stackGraph =
    j), j = g));
    for (j = this.start; j <= this.end; j++) for (k = 0; k < h.length; k++) if (g = h[k], !g.hidden && (l = g.type, g.chart == this.chart && g.valueAxis == this && a == l && g.stackable && (l = this.data[j].axes[this.id].graphs[g.id], g = l.values.value, !isNaN(g) && (f[j] = isNaN(f[j]) ? Math.abs(g)  : f[j] + Math.abs(g), 'regular' == b)))) {
      if ('line' == a || 'step' == a || 'smoothedLine' == a) isNaN(c[j]) ? (c[j] = g, l.values.close = g, l.values.open = this.baseValue)  : (l.values.close = isNaN(g) ? c[j] : g + c[j], l.values.open = c[j], c[j] = l.values.close);
      'column' == a && !isNaN(g) && (l.values.close = g, 0 > g ? (l.values.close = g, isNaN(d[j]) || (l.values.close += d[j], l.values.open = d[j]), d[j] = l.values.close)  : (l.values.close = g, isNaN(e[j]) || (l.values.close += e[j], l.values.open = e[j]), e[j] = l.values.close))
    }
    for (j = this.start; j <= this.end; j++) for (k = 0; k < h.length; k++) g = h[k],
    g.hidden || (l = g.type, g.chart == this.chart && g.valueAxis == this && a == l && g.stackable && (l = this.data[j].axes[this.id].graphs[g.id], g = l.values.value, isNaN(g) || (c = 100 * (g / f[j]), l.values.percents = c, l.values.total = f[j], '100%' == b && (isNaN(d[j]) && (d[j] = 0), isNaN(e[j]) && (e[j] = 0), 0 > c ? (l.values.close = c + d[j], l.values.open = d[j], d[j] = l.values.close)  : (l.values.close = c + e[j], l.values.open = e[j], e[j] = l.values.close)))))
  },
  recalculate: function () {
    for (var a = this.chart.graphs, b = 0; b < a.length; b++) {
      var c = a[b];
      if (c.valueAxis == this) {
        var d = 'value';
        if ('candlestick' == c.type || 'ohlc' == c.type) d = 'open';
        var e,
        f,
        g = this.end + 2,
        g = AmCharts.fitToBounds(this.end + 1, 0, this.data.length - 1),
        h = this.start;
        0 < h && h--;
        for (var j = this.start; j <= g && !(f = this.data[j].axes[this.id].graphs[c.id], e = f.values[d], !isNaN(e)); j++);
        for (d = h; d <= g; d++) {
          f = this.data[d].axes[this.id].graphs[c.id];
          f.percents = {
          };
          var h = f.values,
          l;
          for (l in h) f.percents[l] = 'percents' != l ? 100 * (h[l] / e) - 100 : h[l]
        }
      }
    }
  },
  getMinMax: function () {
    for (var a = !1, b = this.chart, c = b.graphs, d = 0; d < c.length; d++) {
      var e = c[d].type;
      if ('line' == e || 'step' == e || 'smoothedLine' == e) this.expandMinMax && (a = !0)
    }
    a && (0 < this.start && this.start--, this.end < this.data.length - 1 && this.end++);
    'serial' == b.chartType && !0 == b.categoryAxis.parseDates && !a && this.end < this.data.length -
    1 && this.end++;
    this.min = this.getMin(this.start, this.end);
    this.max = this.getMax();
    a = this.guides.length;
    if (this.includeGuidesInMinMax && 0 < a) for (b = 0; b < a; b++) c = this.guides[b],
    c.toValue < this.min && (this.min = c.toValue),
    c.value < this.min && (this.min = c.value),
    c.toValue > this.max && (this.max = c.toValue),
    c.value > this.max && (this.max = c.value);
    isNaN(this.minimum) || (this.min = this.minimum);
    isNaN(this.maximum) || (this.max = this.maximum);
    this.min > this.max && (a = this.max, this.max = this.min, this.min = a);
    isNaN(this.minTemp) || (this.min =
    this.minTemp);
    isNaN(this.maxTemp) || (this.max = this.maxTemp);
    this.minReal = this.min;
    this.maxReal = this.max;
    0 == this.min && 0 == this.max && (this.max = 9);
    this.min > this.max && (this.min = this.max - 1);
    a = this.min;
    b = this.max;
    c = this.max - this.min;
    d = 0 == c ? Math.pow(10, Math.floor(Math.log(Math.abs(this.max)) * Math.LOG10E)) / 10 : Math.pow(10, Math.floor(Math.log(Math.abs(c)) * Math.LOG10E)) / 10;
    isNaN(this.maximum) && isNaN(this.maxTemp) && (this.max = Math.ceil(this.max / d) * d + d);
    isNaN(this.minimum) && isNaN(this.minTemp) && (this.min = Math.floor(this.min /
    d) * d - d);
    0 > this.min && 0 <= a && (this.min = 0);
    0 < this.max && 0 >= b && (this.max = 0);
    '100%' == this.stackType && (this.min = 0 > this.min ? - 100 : 0, this.max = 0 > this.max ? 0 : 100);
    c = this.max - this.min;
    d = Math.pow(10, Math.floor(Math.log(Math.abs(c)) * Math.LOG10E)) / 10;
    this.step = Math.ceil(c / this.gridCount / d) * d;
    c = Math.pow(10, Math.floor(Math.log(Math.abs(this.step)) * Math.LOG10E));
    c = c.toExponential(0).split('e');
    d = Number(c[1]);
    9 == Number(c[0]) && d++;
    c = this.generateNumber(1, d);
    d = Math.ceil(this.step / c);
    5 < d && (d = 10);
    5 >= d && 2 < d && (d = 5);
    this.step =
    Math.ceil(this.step / (c * d)) * c * d;
    1 > c ? (this.maxDecCount = Math.abs(Math.log(Math.abs(c)) * Math.LOG10E), this.maxDecCount = Math.round(this.maxDecCount), this.step = AmCharts.roundTo(this.step, this.maxDecCount + 1))  : this.maxDecCount = 0;
    this.min = this.step * Math.floor(this.min / this.step);
    this.max = this.step * Math.ceil(this.max / this.step);
    0 > this.min && 0 <= a && (this.min = 0);
    0 < this.max && 0 >= b && (this.max = 0);
    1 < this.minReal && 1 < this.max - this.minReal && (this.minReal = Math.floor(this.minReal));
    c = Math.pow(10, Math.floor(Math.log(Math.abs(this.minReal)) *
    Math.LOG10E));
    0 == this.min && (this.minReal = c);
    0 == this.min && 1 < this.minReal && (this.minReal = 1);
    0 < this.min && 0 < this.minReal - this.step && (this.minReal = this.min + this.step < this.minReal ? this.min + this.step : this.min);
    c = Math.log(b) * Math.LOG10E - Math.log(a) * Math.LOG10E;
    this.logarithmic && (2 < c ? (this.minReal = this.min = Math.pow(10, Math.floor(Math.log(Math.abs(a)) * Math.LOG10E)), this.max = Math.pow(10, Math.ceil(Math.log(Math.abs(b)) * Math.LOG10E)))  : (b = Math.pow(10, Math.floor(Math.log(Math.abs(this.min)) * Math.LOG10E)) / 10, a = Math.pow(10, Math.floor(Math.log(Math.abs(a)) * Math.LOG10E)) / 10, b < a && (this.minReal = this.min = 10 * a)))
  },
  generateNumber: function (a, b) {
    var c = '',
    d;
    d = 0 > b ? Math.abs(b) - 1 : Math.abs(b);
    for (var e = 0; e < d; e++) c += '0';
    return 0 > b ? Number('0.' + c + ('' + a))  : Number('' + a + c)
  },
  getMin: function (a, b) {
    for (var c, d = a; d <= b; d++) {
      var e = this.data[d].axes[this.id].graphs,
      f;
      for (f in e) {
        var g = this.chart.getGraphById(f);
        if (g.includeInMinMax && (!g.hidden || this.includeHidden)) {
          isNaN(c) && (c = Infinity);
          this.foundGraphs = !0;
          g = e[f].values;
          this.recalculateToPercents && (g = e[f].percents);
          var h;
          if (this.minMaxField) h = g[this.minMaxField],
          h < c && (c = h);
           else for (var j in g) 'percents' != j && 'total' != j && (h = g[j], h < c && (c = h))
        }
      }
    }
    return c
  },
  getMax: function () {
    for (var a, b = this.start; b <= this.end; b++) {
      var c = this.data[b].axes[this.id].graphs,
      d;
      for (d in c) {
        var e = this.chart.getGraphById(d);
        if (e.includeInMinMax && (!e.hidden || this.includeHidden)) {
          isNaN(a) && (a = - Infinity);
          this.foundGraphs = !0;
          e = c[d].values;
          this.recalculateToPercents && (e = c[d].percents);
          var f;
          if (this.minMaxField) f = e[this.minMaxField],
          f > a && (a = f);
           else for (var g in e) 'percents' != g && 'total' != g && (f = e[g], f > a && (a = f))
        }
      }
    }
    return a
  },
  dispatchZoomEvent: function (a, b) {
    var c = {
      type: 'axisZoomed',
      startValue: a,
      endValue: b
    };
    this.fire(c.type, c)
  },
  zoomToValues: function (a, b) {
    if (b < a) var c = b,
    b = a,
    a = c;
    a < this.min && (a = this.min);
    b > this.max && (b = this.max);
    c = {
      type: 'axisSelfZoomed',
      valueAxis: this
    };
    c.multiplyer = this.axisWidth / Math.abs(this.getCoordinate(b) - this.getCoordinate(a));
    c.position = 'vertical' == this.orientation ? this.reversed ? this.getCoordinate(a) - this.y : this.getCoordinate(b) -
    this.y : this.reversed ? this.getCoordinate(b) - this.x : this.getCoordinate(a) - this.x;
    this.fire(c.type, c)
  },
  coordinateToValue: function (a) {
    return isNaN(a) ? NaN : !0 == this.logarithmic ? Math.pow(10, (this.rotate ? !0 == this.reversed ? (this.axisWidth - a) / this.stepWidth : a / this.stepWidth : !0 == this.reversed ? a / this.stepWidth : (this.axisWidth - a) / this.stepWidth) + Math.log(this.minReal) * Math.LOG10E)  : !0 == this.reversed ? this.rotate ? this.min - (a - this.axisWidth) / this.stepWidth : a / this.stepWidth + this.min : this.rotate ? a / this.stepWidth + this.min :
    this.min - (a - this.axisWidth) / this.stepWidth
  },
  getCoordinate: function (a) {
    if (isNaN(a)) return NaN;
    var b = this.rotate,
    c = this.reversed,
    d = this.axisWidth,
    e = this.stepWidth;
    !0 == this.logarithmic ? (a = Math.log(a) * Math.LOG10E - Math.log(this.minReal) * Math.LOG10E, b = b ? !0 == c ? d - e * a : e * a : !0 == c ? e * a : d - e * a)  : b = !0 == c ? b ? d - e * (a - this.min)  : e * (a - this.min)  : b ? e * (a - this.min)  : d - e * (a - this.min);
    b = Math.round(10 * b) / 10;
    return b = this.rotate ? b + this.x : b + this.y
  },
  synchronizeWithAxis: function (a) {
    this.synchronizeWithAxis = a;
    this.removeListener(this.synchronizeWithAxis, 'axisChanged', this.handleSynchronization);
    this.listenTo(this.synchronizeWithAxis, 'axisChanged', this.handleSynchronization)
  },
  handleSynchronization: function () {
    var a = this.synchronizeWithAxis,
    b = a.min,
    c = a.max,
    a = a.step,
    d = this.synchronizationMultiplyer;
    d && (this.min = b * d, this.max = c * d, this.step = a * d, b = Math.pow(10, Math.floor(Math.log(Math.abs(this.step)) * Math.LOG10E)), b = Math.abs(Math.log(Math.abs(b)) * Math.LOG10E), this.maxDecCount = b = Math.round(b), this.draw())
  }
}); AmCharts.CategoryAxis = AmCharts.Class({
  inherits: AmCharts.AxisBase,
  construct: function () {
    AmCharts.CategoryAxis.base.construct.call(this);
    this.minPeriod = 'DD';
    this.equalSpacing = this.parseDates = !1;
    this.position = 'bottom';
    this.startOnAxis = !1;
    this.firstDayOfWeek = 1;
    this.gridPosition = 'middle';
    this.boldPeriodBeginning = !0;
    this.periods = [
      {
        period: 'ss',
        count: 1
      },
      {
        period: 'ss',
        count: 5
      },
      {
        period: 'ss',
        count: 10
      },
      {
        period: 'ss',
        count: 30
      },
      {
        period: 'mm',
        count: 1
      },
      {
        period: 'mm',
        count: 5
      },
      {
        period: 'mm',
        count: 10
      },
      {
        period: 'mm',
        count: 30
      },
      {
        period: 'hh',
        count: 1
      },
      {
        period: 'hh',
        count: 3
      },
      {
        period: 'hh',
        count: 6
      },
      {
        period: 'hh',
        count: 12
      },
      {
        period: 'DD',
        count: 1
      },
      {
        period: 'WW',
        count: 1
      },
      {
        period: 'MM',
        count: 1
      },
      {
        period: 'MM',
        count: 2
      },
      {
        period: 'MM',
        count: 3
      },
      {
        period: 'MM',
        count: 6
      },
      {
        period: 'YYYY',
        count: 1
      },
      {
        period: 'YYYY',
        count: 2
      },
      {
        period: 'YYYY',
        count: 5
      },
      {
        period: 'YYYY',
        count: 10
      },
      {
        period: 'YYYY',
        count: 50
      },
      {
        period: 'YYYY',
        count: 100
      }
    ];
    this.dateFormats = [
      {
        period: 'fff',
        format: 'JJ:NN:SS'
      },
      {
        period: 'ss',
        format: 'JJ:NN:SS'
      },
      {
        period: 'mm',
        format: 'JJ:NN'
      },
      {
        period: 'hh',
        format: 'JJ:NN'
      },
      {
        period: 'DD',
        format: 'MMM DD'
      },
      {
        period: 'WW',
        format: 'MMM DD'
      },
      {
        period: 'MM',
        format: 'MMM'
      },
      {
        period: 'YYYY',
        format: 'YYYY'
      }
    ];
    this.nextPeriod = {
    };
    this.nextPeriod.fff = 'ss';
    this.nextPeriod.ss = 'mm';
    this.nextPeriod.mm = 'hh';
    this.nextPeriod.hh = 'DD';
    this.nextPeriod.DD = 'MM';
    this.nextPeriod.MM = 'YYYY'
  },
  draw: function () {
    var a = this;
    AmCharts.CategoryAxis.base.draw.call(a);
    a.generateDFObject();
    var b = a.chart.chartData;
    a.data = b;
    if (0 < b.length) {
      var c = a.start,
      d = a.labelFrequency,
      e = 0,
      f = a.end - c + 1,
      g = a.gridCount,
      h = a.showFirstLabel,
      j = a.showLastLabel,
      l,
      k = '',
      n = AmCharts.extractPeriod(a.minPeriod);
      l = AmCharts.getPeriodDuration(n.period, n.count);
      var o,
      m,
      p,
      s,
      q = a.rotate,
      r = a.firstDayOfWeek,
      b = AmCharts.resetDateToMin(new Date(b[b.length - 1].time + 1.05 * l), a.minPeriod, 1, r).getTime();
      a.endTime > b && (a.endTime = b);
      if (a.parseDates && !a.equalSpacing) {
        if (a.timeDifference = a.endTime - a.startTime, c = a.choosePeriod(0), d = c.period, b = c.count, o = AmCharts.getPeriodDuration(d, b), o < l && (d = n.period, b = n.count, o = l), n = d, 'WW' == n && (n = 'DD'), a.stepWidth = a.getStepWidth(a.timeDifference), g = Math.ceil(a.timeDifference / o) + 1, k = AmCharts.resetDateToMin(new Date(a.startTime - o), d, b, r).getTime(), n == d && 1 == b && (m = o * a.stepWidth), a.cellWidth = l * a.stepWidth, f = Math.round(k / o), c = - 1, f / 2 == Math.round(f / 2) && (c = - 2, k -= o), 0 < a.gridCount) for (f = c; f <= g; f++) {
          p = k + 1.5 * o;
          p = AmCharts.resetDateToMin(new Date(p), d, b, r).getTime();
          l = (p - a.startTime) * a.stepWidth;
          l = q ? l + a.y : l + a.x;
          s = !1;
          a.nextPeriod[n] && (s = a.checkPeriodChange(a.nextPeriod[n], 1, p, k));
          var v = !1;
          s ? (k = a.dateFormatsObject[a.nextPeriod[n]], v = !0)  : k = a.dateFormatsObject[n];
          a.boldPeriodBeginning || (v = !1);
          k = AmCharts.formatDate(new Date(p), k);
          if (f == c && !h || f == g && !j) k = ' ';
          l = new a.axisItemRenderer(this, l, k, !1, m, 0, !1, v);
          a.pushAxisItem(l);
          k = p
        }
      } else if (a.parseDates) {
        if (a.parseDates && a.equalSpacing) {
          e = a.start;
          a.startTime = a.data[a.start].time;
          a.endTime = a.data[a.end].time;
          a.timeDifference = a.endTime - a.startTime;
          c = a.choosePeriod(0);
          d = c.period;
          b = c.count;
          o = AmCharts.getPeriodDuration(d, b);
          o < l && (d = n.period, b = n.count, o = l);
          n = d;
          'WW' == n && (n = 'DD');
          a.stepWidth = a.getStepWidth(f);
          g = Math.ceil(a.timeDifference /
          o) + 1;
          k = AmCharts.resetDateToMin(new Date(a.startTime - o), d, b, r).getTime();
          a.cellWidth = a.getStepWidth(f);
          f = Math.round(k / o);
          c = - 1;
          f / 2 == Math.round(f / 2) && (c = - 2, k -= o);
          f = a.start;
          f / 2 == Math.round(f / 2) && f--;
          0 > f && (f = 0);
          m = a.end + 2;
          m >= a.data.length && (m = a.data.length);
          r = !1;
          for (a.end - a.start > a.gridCount && (r = !0); f < m; f++) if (p = a.data[f].time, a.checkPeriodChange(d, b, p, k)) {
            l = a.getCoordinate(f - a.start);
            s = !1;
            a.nextPeriod[n] && (s = a.checkPeriodChange(a.nextPeriod[n], 1, p, k));
            v = !1;
            s ? (k = a.dateFormatsObject[a.nextPeriod[n]], v = !0)  :
            k = a.dateFormatsObject[n];
            k = AmCharts.formatDate(new Date(p), k);
            if (f == c && !h || f == g && !j) k = ' ';
            r ? r = !1 : (l = new a.axisItemRenderer(this, l, k, void 0, void 0, void 0, void 0, v), l.graphics(), a.pushAxisItem(l));
            k = p
          }
        }
      } else if (a.cellWidth = a.getStepWidth(f), f < g && (g = f), e += a.start, a.stepWidth = a.getStepWidth(f), 0 < g) {
        g = Math.floor(f / g);
        f = e;
        f / 2 == Math.round(f / 2) && f--;
        0 > f && (f = 0);
        for (m = 0; f <= a.end + 2; f += g) {
          m++;
          k = 0 <= f && f < a.data.length ? a.data[f].category : '';
          l = a.getCoordinate(f - e);
          r = 0;
          'start' == a.gridPosition && (l -= a.cellWidth / 2, r =
          a.cellWidth / 2);
          if (f == c && !h || f == a.end && !j) k = ' ';
          Math.round(m / d) != m / d && (k = ' ');
          b = a.cellWidth;
          q && (b = NaN);
          l = new a.axisItemRenderer(this, l, k, !0, b, r, void 0, !1, r);
          a.pushAxisItem(l)
        }
      }
      for (f = 0; f < a.data.length; f++) if (h = a.data[f]) a.parseDates && !a.equalSpacing ? (j = (h.time - a.startTime) * a.stepWidth + a.cellWidth / 2, j = q ? j + a.y : j + a.x)  : j = a.getCoordinate(f - e),
      h.x[a.id] = j
    }
    h = a.guides.length;
    for (f = 0; f < h; f++) j = a.guides[f],
    d = g = c = NaN,
    j.toCategory && (d = a.chart.getCategoryIndexByValue(j.toCategory), isNaN(d) || (c = a.getCoordinate(d -
    e), l = new a.axisItemRenderer(this, c, '', !0, NaN, NaN, j), a.pushAxisItem(l))),
    j.category && (d = a.chart.getCategoryIndexByValue(j.category), isNaN(d) || (g = a.getCoordinate(d - e), d = (c - g) / 2, l = new a.axisItemRenderer(this, g, j.label, !0, NaN, d, j), a.pushAxisItem(l))),
    j.toDate && (a.equalSpacing ? (d = a.chart.getClosestIndex(a.data, 'time', j.toDate.getTime(), !1, 0, a.data.length - 1), isNaN(d) || (c = a.getCoordinate(d - e)))  : (c = (j.toDate.getTime() - a.startTime) * a.stepWidth, c = q ? c + a.y : c + a.x), l = new a.axisItemRenderer(this, c, '', !0, NaN, NaN, j), a.pushAxisItem(l)),
    j.date && (a.equalSpacing ? (d = a.chart.getClosestIndex(a.data, 'time', j.date.getTime(), !1, 0, a.data.length - 1), isNaN(d) || (g = a.getCoordinate(d - e)))  : (g = (j.date.getTime() - a.startTime) * a.stepWidth, g = q ? g + a.y : g + a.x), d = (c - g) / 2, l = 'horizontal' == a.orientation ? new a.axisItemRenderer(this, g, j.label, !1, 2 * d, NaN, j)  : new a.axisItemRenderer(this, g, j.label, !1, NaN, d, j), a.pushAxisItem(l)),
    c = new a.guideFillRenderer(this, c - g, g, j),
    g = c.graphics(),
    a.pushAxisItem(c),
    j.graphics = g,
    g.index = f,
    j.balloonText && (g.mouseover(function () {
      a.handleGuideOver(this.index)
    }), g.mouseout(function () {
      a.handleGuideOut(this.index)
    }));
    a.axisCreated = !0;
    q ? a.set.transform('...T' + a.x + ',0')  : a.set.transform('...T0,' + a.y);
    a.positionTitle();
    a.axisLine.set && a.axisLine.set.toFront()
  },
  choosePeriod: function (a) {
    var b = AmCharts.getPeriodDuration(this.periods[a].period, this.periods[a].count);
    return Math.ceil(this.timeDifference / b) <= this.gridCount ? this.periods[a] : a + 1 < this.periods.length ? this.choosePeriod(a + 1)  : this.periods[a]
  },
  getStepWidth: function (a) {
    var b;
    this.startOnAxis ? (b = this.axisWidth / (a - 1), 1 == a && (b = this.axisWidth))  : b = this.axisWidth / a;
    return b
  },
  getCoordinate: function (a) {
    a *= this.stepWidth;
    this.startOnAxis || (a += this.stepWidth / 2);
    return a = this.rotate ? a + this.y : a + this.x
  },
  timeZoom: function (a, b) {
    this.startTime = a;
    this.endTime = b + this.minDuration()
  },
  minDuration: function () {
    var a = AmCharts.extractPeriod(this.minPeriod);
    return AmCharts.getPeriodDuration(a.period, a.count)
  },
  checkPeriodChange: function (a, b, c, d) {
    var d = new Date(d),
    e = this.firstDayOfWeek,
    c = AmCharts.resetDateToMin(new Date(c), a, b, e).getTime(),
    a = AmCharts.resetDateToMin(d, a, b, e).getTime();
    return c != a ? !0 : !1
  },
  generateDFObject: function () {
    this.dateFormatsObject = {
    };
    for (var a = 0; a < this.dateFormats.length; a++) {
      var b = this.dateFormats[a];
      this.dateFormatsObject[b.period] = b.format
    }
  },
  xToIndex: function (a) {
    var b = this.data,
    c = this.chart,
    d = c.rotate,
    e = this.stepWidth,
    a = d ? a - this.y : a - this.x;
    this.parseDates && !this.equalSpacing ? (a = this.startTime + Math.round(a / e) - this.minDuration() / 2, c = c.getClosestIndex(b, 'time', a, !1, this.start, this.end + 1))  : (this.startOnAxis || (a -=
    e / 2), c = this.start + Math.round(a / e));
    var c = AmCharts.fitToBounds(c, 0, b.length - 1),
    f;
    b[c] && (f = b[c].x[this.id]);
    d ? (f > this.height + 1 + this.y && c--, f < this.y && c++)  : (f > this.width + 1 + this.x && c--, f < this.x && c++);
    return c = AmCharts.fitToBounds(c, 0, b.length - 1)
  },
  dateToCoordinate: function (a) {
    return this.parseDates && !this.equalSpacing ? (a.getTime() - this.startTime) * this.stepWidth : this.parseDates && this.equalSpacing ? this.getCoordinate(this.chart.getClosestIndex(this.data, 'time', a.getTime(), !1, 0, this.data.length - 1) - this.start)  :
    NaN
  },
  categoryToCoordinate: function (a) {
    return this.chart ? this.getCoordinate(this.chart.getCategoryIndexByValue(a) - this.start)  : NaN
  },
  coordinateToDate: function (a) {
    return this.equalSpacing ? (a = this.xToIndex(a), new Date(this.data[a].time))  : new Date(this.startTime + a / this.stepWidth)
  }
}); AmCharts.RectangularAxisRenderer = AmCharts.Class({
  construct: function (a) {
    var b = a.axisThickness,
    c = a.axisColor,
    d = a.axisAlpha,
    e = a.offset,
    f = a.dx,
    g = a.dy,
    h = a.visibleAxisX,
    j = a.visibleAxisY,
    l = a.visibleAxisHeight,
    k = a.visibleAxisWidth,
    n = a.chart.container,
    o = n.set();
    this.set = o;
    'horizontal' == a.orientation ? (c = AmCharts.line(n, [
      0,
      k
    ], [
      0,
      0
    ], c, d, b), this.axisWidth = a.width, 'bottom' == a.position ? (a = b / 2 + e + l + j - 1, b = h)  : (a = - b / 2 - e + j + g, b = f + h))  : (this.axisWidth = a.height, 'right' == a.position ? (c = AmCharts.line(n, [
      0,
      0,
      - f
    ], [
      0,
      l,
      l - g
    ], c, d, b), a = j + g, b = b / 2 + e + f + k + h - 1)  : (c = AmCharts.line(n, [
      0,
      0
    ], [
      0,
      l
    ], c, d, b), a = j, b = - b / 2 - e + h));
    o.push(c);
    o.translate(Math.round(b) + ',' + Math.round(a))
  }
}); AmCharts.RectangularAxisItemRenderer = AmCharts.Class({
  construct: function (a, b, c, d, e, f, g, h, j) {
    b = Math.round(b);
    void 0 == c && (c = '');
    j || (j = 0);
    void 0 == d && (d = !0);
    var l = a.chart.fontFamily,
    k = a.fontSize;
    void 0 == k && (k = a.chart.fontSize);
    var n = a.color;
    void 0 == n && (n = a.chart.color);
    var o = a.chart.container,
    m = o.set();
    this.set = m;
    var p = a.axisThickness,
    s = a.axisColor,
    q = a.axisAlpha,
    r = a.tickLength,
    v = a.gridAlpha,
    y = a.gridThickness,
    B = a.gridColor,
    J = a.dashLength,
    t = a.fillColor,
    I = a.fillAlpha,
    H = a.labelsEnabled,
    D = a.labelRotation,
    T =
    a.counter,
    C = a.inside,
    K = a.dx,
    L = a.dy,
    Ia = a.orientation,
    ca = a.position,
    ha = a.previousCoord,
    $ = a.chart.rotate,
    N = a.visibleAxisX,
    pa = a.visibleAxisY,
    Y = a.visibleAxisHeight,
    U = a.visibleAxisWidth,
    da = a.offset,
    V,
    O;
    g ? (H = !0, isNaN(g.tickLength) || (r = g.tickLength), void 0 != g.lineColor && (B = g.lineColor), isNaN(g.lineAlpha) || (v = g.lineAlpha), isNaN(g.dashLength) || (J = g.dashLength), isNaN(g.lineThickness) || (y = g.lineThickness), !0 == g.inside && (C = !0), isNaN(g.labelRotation) || (D = g.labelRotation))  : c || (v /= 3, r /= 2);
    O = 'start';
    e && (O = 'middle');
    var Z = D * Math.PI / 180,
    ia,
    z = 0,
    u = 0,
    ea = 0,
    fa = ia = 0,
    Aa = N + K + ',' + (pa + L) + ',' + U + ',' + Y;
    'vertical' == Ia && (D = 0);
    if (H) {
      var P = AmCharts.text(o, 0, 0, c, {
        fill: n,
        'text-anchor': O,
        'font-family': l,
        'font-size': k
      });
      !0 == h && P.attr({
        'font-weight': 'bold'
      });
      m.push(P);
      fa = P.getBBox().width
    }
    if ('horizontal' == Ia) {
      if (b >= N && b <= U + 1 + N && (V = AmCharts.line(o, [
        b + j,
        b + j
      ], [
        0,
        r
      ], s, q, y), b + j > U + N + 1 ? (V.remove(), V = null)  : m.push(V), O = AmCharts.line(o, [
        b,
        b + K,
        b + K
      ], [
        Y,
        Y + L,
        L
      ], B, v, y, J), m.push(O)), u = 0, z = b, g && 90 == D && (z -= k / 2 + 3), !1 == d ? (O = 'start', $ || (u = 'bottom' == ca ?
      C ? u + r : u - r : C ? u - r : u + r, z += 3, e && (z += e / 2, O = 'middle')))  : O = 'middle', 1 == T && 0 < I && !g && (ia = b - ha, fill = AmCharts.rect(o, ia, a.height, [
        t
      ], [
        I
      ]), fill.translate(b - ia + K + ',' + L), fill.attr({
        'clip-rect': Aa
      }), m.push(fill)), 'bottom' == ca ? (u += Y + k / 2 + da, C ? 0 < D ? (u = Y - fa / 2 * Math.sin(Z) - r - 3, z += fa / 2 * Math.cos(Z))  : u -= r + k + 3 + 3 : 0 < D ? (u = Y + fa / 2 * Math.sin(Z) + r + 3, z -= fa / 2 * Math.cos(Z))  : u += r + p + 3 + 3)  : (u += L + k / 2 - da, z += K, C ? 0 < D ? (u = fa / 2 * Math.sin(Z) + r + 3, z -= fa / 2 * Math.cos(Z))  : u += r + 3 : 0 < D ? (u = - (fa / 2) * Math.sin(Z) - r - 3, z += fa / 2 * Math.cos(Z))  : u -= r + k + 3 + p + 3), 'bottom' ==
      ca ? ia = (C ? Y - r - 1 : Y + p - 1) + da : (ea = K, ia = (C ? L : L - r - p + 1) - da), f && (z += f), L = z, 0 < D && (L += fa / 2 * Math.cos(Z)), P && (L > N + U + 1 || L < N)) m.exclude(P),
      P.remove(),
      P = null
    } else {
      b >= pa && b <= Y + 1 + pa && (V = AmCharts.line(o, [
        0,
        r
      ], [
        b + j,
        b + j
      ], s, q, y), b + j > Y + pa + 1 ? (V.remove(), V = null)  : m.push(V), O = AmCharts.line(o, [
        0,
        K,
        U + K
      ], [
        b,
        b + L,
        b + L
      ], B, v, y, J), m.push(O));
      O = 'end';
      if (!0 == C && 'left' == ca || !1 == C && 'right' == ca) O = 'start';
      u = b - k / 2;
      1 == T && 0 < I && !g && (N = b - ha, fill = AmCharts.rect(o, a.width, N, [
        t
      ], [
        I
      ]), fill.translate(K + ',' + (b - N + L)), fill.attr({
        'clip-rect': Aa
      }), m.push(fill));
      u += k / 2;
      'right' == ca ? (z += K + U + da, u += L, !0 == C ? (z -= r + 4, f || (u -= k / 2 + 3))  : (z += r + 4 + p, u -= 2))  : !0 == C ? (z += r + 4 - da, f || (u -= k / 2 + 3), g && (z += K, u += L))  : (z += - r - p - 4 - 2 - da, u -= 2);
      V && ('right' == ca ? (ea += K + da + U, ia += L, ea = !0 == C ? ea - p : ea + p)  : (ea -= da, !0 != C && (ea -= r + p)));
      f && (u += f);
      U = pa - 3;
      'right' == ca && (U += L);
      if (P && (u > Y + pa + 1 || u < U)) m.exclude(P),
      P.remove(),
      P = null
    }
    V && V.translate(ea + ',' + ia);
    !1 == a.visible && (V && (m.exclude(V), V.remove()), P && (m.exclude(P), P.remove(), P = null));
    P && (P.attr({
      'text-anchor': O
    }), P.transform('r' + - D), P.transform('...T' + z + ',' +
    u), a.allLabels.push(P));
    a.counter = 0 == T ? 1 : 0;
    a.previousCoord = b
  },
  graphics: function () {
    return this.set
  }
}); AmCharts.RectangularAxisGuideFillRenderer = AmCharts.Class({
  construct: function (a, b, c, d) {
    var e = a.orientation,
    f = 0,
    g = d.fillAlpha,
    h = a.chart.container,
    j = a.dx,
    l = a.dy;
    isNaN(b) && (b = 4, f = 2, g = 0);
    this.set = h.set();
    var k = d.fillColor;
    void 0 == k && (k = '#000000');
    0 > b && 'object' == typeof k && (k = k.join(',').split(',').reverse());
    isNaN(g) && (g = 0);
    d = a.visibleAxisX + j + ',' + (a.visibleAxisY + l) + ',' + a.visibleAxisWidth + ',' + a.visibleAxisHeight;
    'vertical' == e ? (a = AmCharts.rect(h, a.width, b, k, g), a.translate(j + ',' + (c - f + l)))  : (a = AmCharts.rect(h, b, a.height, k, g), a.translate(c - f + j + ',' + l));
    a.attr({
      'clip-rect': d
    });
    this.set.push(a)
  },
  graphics: function () {
    return this.set
  }
}); AmCharts.RadarAxisRenderer = AmCharts.Class({
  construct: function (a) {
    var b = a.chart,
    c = a.axisThickness,
    d = a.axisColor,
    e = a.axisAlpha,
    f = a.x,
    g = a.y;
    this.set = b.container.set();
    var h = a.axisTitleOffset,
    j = a.radarCategoriesEnabled,
    l = a.chart.fontFamily,
    k = a.fontSize;
    void 0 == k && (k = a.chart.fontSize);
    var n = a.color;
    void 0 == n && (n = a.chart.color);
    if (b) {
      this.axisWidth = a.height;
      for (var a = b.chartData, o = a.length, m = 0; m < o; m++) {
        var p = 180 - 360 / o * m,
        s = f + this.axisWidth * Math.sin(p / 180 * Math.PI),
        q = g + this.axisWidth * Math.cos(p / 180 * Math.PI);
        this.set.push(AmCharts.line(b.container, [
          f,
          s
        ], [
          g,
          q
        ], d, e, c));
        if (j) {
          var s = 'start',
          q = f + (this.axisWidth + h) * Math.sin(p / 180 * Math.PI),
          r = g + (this.axisWidth + h) * Math.cos(p / 180 * Math.PI);
          if (180 == p || 0 == p) s = 'middle',
          q -= 5;
          0 > p && (s = 'end', q -= 10);
          180 == p && (r -= 5);
          0 == p && (r += 5);
          p = AmCharts.text(b.container, q + 5, r, a[m].category, {
            fill: n,
            'text-anchor': s,
            'font-family': l,
            'font-size': k
          });
          this.set.push(p);
          p.getBBox()
        }
      }
    }
  }
}); AmCharts.RadarAxisItemRenderer = AmCharts.Class({
  construct: function (a, b, c, d, e, f, g) {
    void 0 == c && (c = '');
    var h = a.chart.fontFamily,
    j = a.fontSize;
    void 0 == j && (j = a.chart.fontSize);
    var l = a.color;
    void 0 == l && (l = a.chart.color);
    var k = a.chart.container;
    this.set = k.set();
    var n = a.axisColor,
    o = a.axisAlpha,
    m = a.tickLength,
    p = a.gridAlpha,
    s = a.gridThickness,
    q = a.gridColor,
    r = a.dashLength,
    v = a.fillColor,
    y = a.fillAlpha,
    B = a.labelsEnabled,
    d = a.counter,
    J = a.inside,
    t = a.gridType,
    b = b - a.height,
    I,
    e = a.x,
    f = a.y;
    g ? (B = !0, isNaN(g.tickLength) || (m =
    g.tickLength), void 0 != g.lineColor && (q = g.lineColor), isNaN(g.lineAlpha) || (p = g.lineAlpha), isNaN(g.dashLength) || (r = g.dashLength), isNaN(g.lineThickness) || (s = g.lineThickness), !0 == g.inside && (J = !0))  : c || (p /= 3, m /= 2);
    var H = 'end',
    D = - 1;
    J && (H = 'start', D = 1);
    if (B) {
      var T = AmCharts.text(k, e + (m + 3) * D, b, c, {
        fill: l,
        'text-anchor': H,
        'font-family': h,
        'font-size': j
      });
      this.set.push(T);
      I = AmCharts.line(k, [
        e,
        e + m * D
      ], [
        b,
        b
      ], n, o, s);
      this.set.push(I)
    }
    b = a.y - b;
    if ('polygons' == t) {
      for (var C = [
      ], K = [
      ], L = a.data.length, c = 0; c < L; c++) h = 180 - 360 / L * c,
      C.push(b * Math.sin(h / 180 * Math.PI)),
      K.push(b * Math.cos(h / 180 * Math.PI));
      C.push(C[0]);
      K.push(K[0]);
      c = AmCharts.line(k, C, K, q, p, s, r)
    } else c = AmCharts.circle(k, b, '#FFFFFF', 0, s, q, p);
    c.translate(e + ',' + f);
    this.set.push(c);
    if (1 == d && 0 < y && !g) {
      g = a.previousCoord;
      if ('polygons' == t) {
        for (c = L; 0 <= c; c--) h = 180 - 360 / L * c,
        C.push(g * Math.sin(h / 180 * Math.PI)),
        K.push(g * Math.cos(h / 180 * Math.PI));
        C = AmCharts.polygon(k, C, K, [
          v
        ], [
          y
        ])
      } else C = AmCharts.wedge(k, 0, 0, 0, - 360, b, b, g, 0, {
        fill: v,
        'fill-opacity': y,
        stroke: 0,
        'stroke-opacity': 0,
        'stroke-width': 0
      });
      this.set.push(C);
      C.translate(e + ',' + f)
    }
    !1 == a.visible && (I && I.hide(), T && T.hide());
    a.counter = 0 == d ? 1 : 0;
    a.previousCoord = b
  },
  graphics: function () {
    return this.set
  }
}); AmCharts.RadarAxisGuideFillRenderer = AmCharts.Class({
  construct: function (a, b, c, d) {
    var e = a.chart.container,
    f = d.fillAlpha,
    g = d.fillColor,
    c = a.y - (c - a.height) - b,
    h = c + b,
    j = - d.angle,
    d = - d.toAngle;
    isNaN(j) && (j = 0);
    isNaN(d) && (d = - 360);
    this.set = e.set();
    void 0 == g && (g = '#000000');
    isNaN(f) && (f = 0);
    if ('polygons' == a.gridType) {
      for (var b = [
      ], d = [
      ], l = a.data.length, k = 0; k < l; k++) j = 180 - 360 / l * k,
      b.push(c * Math.sin(j / 180 * Math.PI)),
      d.push(c * Math.cos(j / 180 * Math.PI));
      b.push(b[0]);
      d.push(d[0]);
      for (k = l; 0 <= k; k--) j = 180 - 360 / l * k,
      b.push(h * Math.sin(j /
      180 * Math.PI)),
      d.push(h * Math.cos(j / 180 * Math.PI));
      this.fill = AmCharts.polygon(e, b, d, [
        g
      ], [
        f
      ])
    } else h = c - Math.abs(b),
    this.fill = AmCharts.wedge(e, 0, 0, j, d - j, c, c, h, 0, {
      fill: g,
      'fill-opacity': f,
      stroke: 0,
      'stroke-opacity': 0,
      'stroke-width': 0
    });
    this.set.push(this.fill);
    this.fill.translate(a.x + ',' + a.y)
  },
  graphics: function () {
    return this.set
  }
}); AmCharts.AmGraph = AmCharts.Class({
  construct: function () {
    this.createEvents('rollOverGraphItem', 'rollOutGraphItem', 'clickGraphItem', 'doubleClickGraphItem');
    this.type = 'line';
    this.stackable = !0;
    this.columnCount = 1;
    this.columnIndex = 0;
    this.centerCustomBullets = this.showBalloon = !0;
    this.maxBulletSize = 50;
    this.minBulletSize = 0;
    this.balloonText = '[[value]]';
    this.hidden = this.scrollbar = this.animationPlayed = !1;
    this.columnWidth = 0.8;
    this.pointPosition = 'middle';
    this.depthCount = 1;
    this.includeInMinMax = !0;
    this.negativeBase =
    0;
    this.visibleInLegend = !0;
    this.showAllValueLabels = !1;
    this.showBalloonAt = 'close';
    this.lineThickness = 1;
    this.dashLength = 0;
    this.connect = !0;
    this.lineAlpha = 1;
    this.bullet = 'none';
    this.bulletBorderThickness = 2;
    this.bulletAlpha = this.bulletBorderAlpha = 1;
    this.bulletSize = 8;
    this.hideBulletsCount = this.bulletOffset = 0;
    this.labelPosition = 'top';
    this.cornerRadiusTop = 0;
    this.cursorBulletAlpha = 1;
    this.gradientOrientation = 'vertical';
    this.dy = this.dx = 0;
    this.periodValue = ''
  },
  draw: function () {
    this.container = this.chart.container;
    this.destroy();
    this.set = this.container.set();
    this.ownColumns = [
    ];
    this.allBullets = [
    ];
    this.objectsToAddListeners = [
    ];
    this.positiveObjectsToClip = [
    ];
    this.negativeObjectsToClip = [
    ];
    this.animationArray = [
    ];
    if (this.data && 0 < this.data.length) {
      var a = !1;
      'xy' == this.chartType ? this.xAxis.axisCreated && this.yAxis.axisCreated && (a = !0)  : this.valueAxis.axisCreated && (this.columnsArray = [
      ], a = !0);
      !this.hidden && a && this.createGraph()
    }
  },
  createGraph: function () {
    'inside' == this.labelPosition && (this.labelPosition = 'bottom');
    this.sDur =
    this.chart.startDuration;
    this.sEff = this.chart.startEffect;
    this.startAlpha = this.chart.startAlpha;
    this.seqAn = this.chart.sequencedAnimation;
    this.baseCoord = this.valueAxis.baseCoord;
    this.fillColors || (this.fillColors = [
      this.lineColor
    ]);
    void 0 == this.fillAlphas && (this.fillAlphas = 0);
    void 0 == this.bulletColor && (this.bulletColor = this.lineColor, this.bulletColorNegative = this.negativeLineColor);
    void 0 == this.bulletAlpha && (this.bulletAlpha = this.lineAlpha);
    this.bulletBorderColor || (this.bulletBorderAlpha = 0);
    if (!isNaN(this.valueAxis.min) &&
    !isNaN(this.valueAxis.max)) {
      switch (this.chartType) {
        case 'serial':
          this.createSerialGraph();
          break;
        case 'radar':
          this.createRadarGraph();
          break;
        case 'xy':
          this.createXYGraph()
      }
      this.animationPlayed = !0
    }
  },
  createXYGraph: function () {
    var a = [
    ],
    b = [
    ];
    this.pmh = this.yAxis.visibleAxisHeight + 1;
    this.pmw = this.xAxis.visibleAxisWidth + 1;
    this.pmx = this.yAxis.visibleAxisX;
    this.pmy = this.yAxis.visibleAxisY;
    for (var c = this.start; c <= this.end; c++) {
      var d = this.data[c].axes[this.xAxis.id].graphs[this.id],
      e = d.values.x,
      f = d.values.y,
      g =
      this.xAxis.getCoordinate(e),
      h = this.yAxis.getCoordinate(f);
      !isNaN(e) && !isNaN(f) && (a.push(g), b.push(h), (e = this.createBullet(d, g, h, c)) || (e = 0), this.labelText && this.positionLabel(this.createLabel(d, g, h), this.labelPosition, e))
    }
    this.drawLineGraph(a, b);
    this.launchAnimation()
  },
  createRadarGraph: function () {
    for (var a = this.valueAxis.stackType, b = [
    ], c = [
    ], d, e, f = this.start; f <= this.end; f++) {
      var g = this.data[f].axes[this.valueAxis.id].graphs[this.id],
      h;
      h = 'none' == a || '3d' == a ? g.values.value : g.values.close;
      if (isNaN(h)) this.drawLineGraph(b, c),
      b = [
      ],
      c = [
      ];
       else {
        var j = this.y - (this.valueAxis.getCoordinate(h) - this.height),
        l = 180 - 360 / (this.end - this.start + 1) * f;
        h = j * Math.sin(l / 180 * Math.PI);
        j *= Math.cos(l / 180 * Math.PI);
        b.push(h);
        c.push(j);
        (l = this.createBullet(g, h, j, f)) || (l = 0);
        this.labelText && this.positionLabel(this.createLabel(g, h, j), this.labelPosition, l);
        isNaN(d) && (d = h);
        isNaN(e) && (e = j)
      }
    }
    b.push(d);
    c.push(e);
    this.drawLineGraph(b, c);
    this.set.translate(this.x, this.y);
    this.launchAnimation();
    if (a = this.objectsToAddListeners) for (b = 0; b < a.length; b++) this.addHoverListeners(a[b]),
    this.addClickListeners(a[b])
  },
  positionLabel: function (a, b, c) {
    var d = a.attr('x'),
    e = a.attr('y'),
    f = a.getBBox();
    switch (b) {
      case 'left':
        d -= (f.width + c) / 2 + 5;
        break;
      case 'top':
        e -= (c + f.height) / 2 + 3;
        break;
      case 'right':
        d += (f.width + c) / 2 + 5;
        break;
      case 'bottom':
        e += (c + f.height) / 2 + 3
    }
    a.attr({
      x: d,
      y: e
    })
  },
  createSerialGraph: function () {
    var a = this,
    b = a.id,
    c = a.index,
    d = a.data,
    e = a.chart.container,
    f = a.valueAxis,
    g = a.type,
    h = a.columnWidth,
    j = a.width,
    l = a.height,
    k = a.x,
    n = a.y,
    o = a.rotate,
    m = a.columnCount,
    p = AmCharts.toCoordinate(a.cornerRadiusTop, h / 2),
    s = a.connect,
    q = [
    ],
    r = [
    ],
    v,
    y,
    B = a.chart.graphs.length,
    J,
    t = a.dx / a.depthCount,
    I = a.dy / a.depthCount,
    H = f.stackType,
    D = a.labelPosition,
    T = a.start,
    C = a.end,
    K = a.scrollbar,
    L = a.categoryAxis,
    Ia = a.baseCoord,
    ca = a.negativeBase,
    ha = a.columnIndex,
    $ = a.lineThickness,
    N = a.lineAlpha,
    pa = a.lineColor,
    Y = a.dashLength,
    U = a.set;
    'above' == D && (D = 'top');
    'below' == D && (D = 'bottom');
    var da,
    V = 270;
    'horizontal' == a.gradientOrientation && (V = 0);
    var O = a.chart.columnSpacing,
    Z = L.cellWidth,
    ia = (Z * h - m) / m;
    O > ia && (O = ia);
    var z,
    u,
    ea,
    fa = l + 1,
    Aa = j + 1,
    P = k,
    jb =
    n,
    Ta,
    Ua,
    Ja,
    Na,
    qb = a.fillColors,
    Ka = a.negativeFillColors,
    ya = a.negativeLineColor,
    za = a.fillAlphas,
    La = a.negativeFillAlphas;
    'object' == typeof za && (za = za[0]);
    'object' == typeof La && (La = La[0]);
    var Oa = f.getCoordinate(f.min);
    f.logarithmic && (Oa = f.getCoordinate(f.minReal));
    a.minCoord = Oa;
    a.resetBullet && (a.bullet = 'none');
    if (!K && ('line' == g || 'smoothedLine' == g || 'step' == g)) if (1 == d.length && 'step' != g && 'none' == a.bullet && (a.bullet = 'round', a.resetBullet = !0), Ka || void 0 != ya) {
      var Ba = ca;
      Ba > f.max && (Ba = f.max);
      Ba < f.min && (Ba = f.min);
      f.logarithmic && (Ba = f.minReal);
      var qa = f.getCoordinate(Ba),
      kb = f.getCoordinate(f.max);
      o ? (fa = l, Aa = Math.abs(kb - qa), Ta = l, Ua = Math.abs(Oa - qa), Na = Ja = n, f.reversed ? (P = k, Ja = qa)  : (P = qa, Ja = k))  : (Aa = j, fa = Math.abs(kb - qa), Ua = j, Ta = Math.abs(Oa - qa), Ja = P = k, f.reversed ? (Na = n, jb = qa)  : Na = qa)
    }
    a.pmx = P;
    a.pmy = jb;
    a.pmh = fa;
    a.pmw = Aa;
    a.nmx = Ja;
    a.nmy = Na;
    a.nmh = Ta;
    a.nmw = Ua;
    h = 'column' == g ? (Z * h - O * (m - 1)) / m : Z * h;
    1 > h && (h = 1);
    var M;
    if ('line' == g || 'step' == g || 'smoothedLine' == g) {
      if (0 < T) for (M = T - 1; - 1 < M; M--) if (z = d[M], u = z.axes[f.id].graphs[b], ea = u.values.value) {
        T =
        M;
        break
      }
      if (C < d.length - 1) for (M = C + 1; M < d.length; M++) if (z = d[M], u = z.axes[f.id].graphs[b], ea = u.values.value) {
        C = M;
        break
      }
    }
    C < d.length - 1 && C++;
    var ka = [
    ],
    la = [
    ],
    Va = !1;
    if ('line' == g || 'step' == g || 'smoothedLine' == g) if (a.stackable && 'regular' == H || '100%' == H) Va = !0;
    for (M = T; M <= C; M++) {
      z = d[M];
      u = z.axes[f.id].graphs[b];
      u.index = M;
      var Wa = '';
      u.url && (Wa = 'pointer');
      var ra,
      Ca = NaN,
      x = NaN,
      w = NaN,
      Q = NaN,
      R = NaN,
      Pa = NaN,
      Da = NaN,
      Qa = NaN,
      Ea = NaN,
      aa = NaN,
      ba = NaN,
      sa = NaN,
      ta = NaN,
      S = NaN,
      A = void 0,
      ua = qb,
      Xa = za,
      X = pa;
      void 0 != u.color && (ua = [
        u.color
      ]);
      u.fillColors && (ua = u.fillColors);
      isNaN(u.alpha) || (Xa = [
        u.alpha
      ]);
      var ma = u.values;
      f.recalculateToPercents && (ma = u.percents);
      if (ma) {
        S = !a.stackable || 'none' == H || '3d' == H ? ma.value : ma.close;
        if ('candlestick' == g || 'ohlc' == g) var S = ma.close,
        Ya = ma.low,
        Da = f.getCoordinate(Ya),
        Za = ma.high,
        Ea = f.getCoordinate(Za);
        var ga = ma.open,
        w = f.getCoordinate(S);
        isNaN(ga) || (R = f.getCoordinate(ga));
        if (!K) switch (a.showBalloonAt) {
          case 'close':
            u.y = w;
            break;
          case 'open':
            u.y = R;
            break;
          case 'high':
            u.y = Ea;
            break;
          case 'low':
            u.y = Da
        }
        var Ca = z.x[L.id],
        Ma = Z / 2,
        $a = Z /
        2;
        'start' == a.pointPosition && (Ca -= Z / 2, Ma = 0, $a = Z);
        K || (u.x = Ca);
        o ? (x = w, Q = R, R = w = Ca, isNaN(ga) && (Q = Ia), Pa = Da, Qa = Ea)  : (Q = x = Ca, isNaN(ga) && (R = Ia));
        switch (g) {
          case 'line':
            isNaN(S) ? s || (a.drawLineGraph(q, r, ka, la), q = [
            ], r = [
            ], ka = [
            ], la = [
            ])  : (S < ca && (u.isNegative = !0), q.push(x), r.push(w), aa = x, ba = w, sa = x, ta = w, Va && (ka.push(Q), la.push(R)));
            break;
          case 'smoothedLine':
            isNaN(S) ? s || (a.drawSmoothedGraph(q, r, ka, la), q = [
            ], r = [
            ], ka = [
            ], la = [
            ])  : (S < ca && (u.isNegative = !0), q.push(x), r.push(w), aa = x, ba = w, sa = x, ta = w, Va && (ka.push(Q), la.push(R)));
            break;
          case 'step':
            isNaN(S) ? s || (a.drawLineGraph(q, r, ka, la), q = [
            ], r = [
            ], ka = [
            ], la = [
            ])  : (S < ca && (u.isNegative = !0), o ? (v && s && (q.push(v), r.push(w - Ma)), r.push(w - Ma), q.push(x), r.push(w + $a), q.push(x))  : (y && s && (r.push(y), q.push(x - Ma)), q.push(x - Ma), r.push(w), q.push(x + $a), r.push(w)), v = x, y = w, aa = x, ba = w, sa = x, ta = w);
            break;
          case 'column':
            if (!isNaN(S)) {
              S < ca && (u.isNegative = !0, Ka && (ua = Ka), void 0 != ya && (X = ya));
              var lb = f.min,
              mb = f.max;
              if (!(S < lb && (ga < lb || void 0 == ga) || S > mb && ga > mb)) if (o) {
                if ('3d' == H) var E = w - 0.5 * (h + O) + O / 2 + I * ha,
                G = Q + t *
                ha;
                 else E = w - (m / 2 - ha) * (h + O) + O / 2,
                G = Q;
                var F = h,
                aa = x,
                ba = E + h / 2,
                sa = x,
                ta = E + h / 2;
                E + F > n + l && (F = n + l - E);
                E < n && (F -= n - E, E = n);
                var ja = x - Q,
                rb = G,
                G = AmCharts.fitToBounds(G, k, k + j),
                ja = ja + (rb - G),
                ja = AmCharts.fitToBounds(ja, k - G, k + j - G);
                E < n + l && 0 < F && (A = new AmCharts.Cuboid(e, ja, F, t, I, ua, za, $, X, N, V, p), A.y(E), A.x(G), 'bottom' != D && (D = 'right', 0 > S ? D = 'left' : (aa += a.dx, 'regular' != H && '100%' != H && (ba += a.dy))))
              } else {
                '3d' == H ? (G = x - 0.5 * (h + O) + O / 2 + t * ha, E = R + I * ha)  : (G = x - (m / 2 - ha) * (h + O) + O / 2, E = R);
                F = h;
                aa = G + h / 2;
                ba = w;
                sa = G + h / 2;
                ta = w;
                G + F > k + j + ha * t && (F = k + j - G +
                ha * t);
                G < k && (F -= k - G, G = k);
                var ja = w - R,
                sb = E,
                E = AmCharts.fitToBounds(E, n, n + l),
                ja = ja + (sb - E),
                ja = AmCharts.fitToBounds(ja, n - E, n + l - E);
                G < k + j + ha * t && 0 < F && (A = new AmCharts.Cuboid(e, F, ja, t, I, ua, za, $, X, a.lineAlpha, V, p), A.y(E), A.x(G), 0 > S ? D = 'bottom' : ('regular' != H && '100%' != H && (aa += a.dx), ba += a.dy))
              }
              if (A) {
                if (!K) {
                  'none' == H && (J = o ? (a.end + 1 - M) * B - c : B * M + c);
                  '3d' == H && (o ? (J = (B - c) * (a.end + 1 - M), ba = E + h / 2)  : (J = (B - c) * (M + 1), aa += t * a.columnIndex), ba += I * a.columnIndex);
                  if ('regular' == H || '100%' == H) D = 'middle',
                  J = o ? 0 < ma.value ? (a.end + 1 - M) * B + c : (a.end +
                  1 - M) * B - c : 0 < ma.value ? B * M + c : B * M - c;
                  a.columnsArray.push({
                    column: A,
                    depth: J
                  });
                  u.x = o ? A.getY() + F / 2 : A.getX() + F / 2;
                  a.ownColumns.push(A);
                  if (0 == a.dx && 0 == a.dy && 0 < a.sDur && !a.animationPlayed) {
                    var va,
                    wa;
                    o ? (ra = x - Q, va = x, wa = Q)  : (ra = w - R, va = w, wa = R);
                    a.seqAn ? (A.set.hide(), a.animationArray.push({
                      obj: A.set,
                      fh: ra,
                      ip: wa,
                      fp: va
                    }), da = setTimeout(function () {
                      a.animate.call(a)
                    }, 1000 * a.sDur / (a.end - a.start + 1) * (M - a.start)), a.timeOuts.push(da))  : a.animate(A.set, ra, wa, va)
                  }
                  for (var xa = A.set, na = 0; na < xa.length; na++) xa[na].dItem = u,
                  xa[na].attr({
                    cursor: Wa
                  });
                  a.objectsToAddListeners.push(A.set)
                }
                U.push(A.set);
                u.columnSprite = xa
              }
            }
            break;
          case 'candlestick':
            if (!isNaN(ga) && !isNaN(Za) && !isNaN(Ya) && !isNaN(S)) {
              var Ra,
              ab;
              S < ga && (u.isNegative = !0, Ka && (ua = Ka), La && (Xa = La), void 0 != ya && (X = ya));
              if (o) {
                if (E = w - h / 2, G = Q, F = h, E + F > n + l && (F = n + l - E), E < n && (F -= n - E, E = n), E < n + l && 0 < F) {
                  var bb,
                  cb;
                  S > ga ? (bb = [
                    x,
                    Qa
                  ], cb = [
                    Q,
                    Pa
                  ])  : (bb = [
                    Q,
                    Qa
                  ], cb = [
                    x,
                    Pa
                  ]);
                  w < n + l && w > n && (Ra = AmCharts.line(e, bb, [
                    w,
                    w
                  ], X, N, $), ab = AmCharts.line(e, cb, [
                    w,
                    w
                  ], X, N, $));
                  1 > Math.abs(x - Q) ? (A = new AmCharts.line(e, [
                    0,
                    0
                  ], [
                    0,
                    F
                  ], X, N, 1), A.attr({
                    x: G,
                    y: E
                  }))  : (A = new AmCharts.Cuboid(e, x - Q, F, t, I, ua, za, $, X, N, V, p), A.y(E), A.x(G))
                }
              } else if (G = x - h / 2, E = R + $ / 2, F = h, G + F > k + j && (F = k + j - G), G < k && (F -= k - G, G = k), G < k + j && 0 < F) {
                1 > Math.abs(w - R) ? (A = new AmCharts.line(e, [
                  0,
                  F
                ], [
                  0,
                  0
                ], X, N, 1), A.translate(G + ',' + E))  : (A = new AmCharts.Cuboid(e, F, w - R, t, I, ua, Xa, $, X, N, V, p), A.x(G), A.y(E));
                var db,
                eb;
                S > ga ? (db = [
                  w,
                  Ea
                ], eb = [
                  R,
                  Da
                ])  : (db = [
                  R,
                  Ea
                ], eb = [
                  w,
                  Da
                ]);
                x < k + j && x > k && (Ra = AmCharts.line(e, [
                  x,
                  x
                ], db, X, N, $), ab = AmCharts.line(e, [
                  x,
                  x
                ], eb, X, N, $))
              }
              if (A && (A.set ? U.push(A.set)  : U.push(A), Ra && (U.push(Ra), U.push(ab)), aa = x, ba = w, sa = x, ta = w, !K)) {
                if (A.getX) var nb = A.getX(),
                ob = A.getY();
                 else nb = G,
                ob = E;
                u.x = o ? ob + F / 2 : nb + F / 2;
                0 == a.dx && 0 == a.dy && 0 < a.sDur && !a.animationPlayed && (o ? (ra = x - Q, va = x, wa = Q)  : (ra = w - R, va = w, wa = R), a.seqAn ? (A.set.show(), a.animationArray.push({
                  obj: A.set,
                  fh: ra,
                  ip: wa,
                  fp: va
                }), da = setTimeout(function () {
                  a.animate.call(a)
                }, 1000 * a.sDur / (a.end - a.start + 1) * (M - a.start)), a.timeOuts.push(da))  : a.animate(A.set, ra, wa, va));
                if (A.set) {
                  xa = A.set;
                  for (na = 0; na < xa.length; na++) xa[na].dItem = u,
                  xa[na].attr({
                    cursor: Wa
                  });
                  a.objectsToAddListeners.push(A.set)
                }
              }
            }
            break;
          case 'ohlc':
            if (!isNaN(ga) && !isNaN(Za) && !isNaN(Ya) && !isNaN(S)) {
              S < ga && (u.isNegative = !0, void 0 != ya && (X = ya));
              var fb,
              gb,
              hb;
              o ? (gb = AmCharts.line(e, [
                Q,
                Q
              ], [
                w - h / 2,
                w
              ], X, N, $, Y), fb = AmCharts.line(e, [
                Pa,
                Qa
              ], [
                w,
                w
              ], X, N, $, Y), hb = AmCharts.line(e, [
                x,
                x
              ], [
                w,
                w + h / 2
              ], X, N, $, Y))  : (gb = AmCharts.line(e, [
                x - h / 2,
                x
              ], [
                R,
                R
              ], X, N, $, Y), fb = AmCharts.line(e, [
                x,
                x
              ], [
                Da,
                Ea
              ], X, N, $, Y), hb = AmCharts.line(e, [
                x,
                x + h / 2
              ], [
                w,
                w
              ], X, N, $, Y));
              U.push(gb);
              U.push(fb);
              U.push(hb);
              aa = x;
              ba = w;
              sa = x;
              ta = w
            }
        }
        if (!K && !isNaN(S)) {
          var pb = a.hideBulletsCount;
          if (a.end - a.start <= pb ||
          0 == pb) {
            var Fa = a.createBullet(u, sa, ta, M);
            Fa || (Fa = 0);
            if (a.labelText) {
              var W = a.createLabel(u, 0, 0);
              'column' == g && (o ? 'right' == D || 'bottom' == D ? W.attr({
                width: j
              })  : W.attr({
                width: x - Q
              })  : W.attr({
                width: Z
              }));
              var Ga = 0,
              Sa = 0,
              oa = W.getBBox(),
              Ha = oa.width,
              ib = oa.height;
              switch (D) {
                case 'left':
                  Ga = - (Ha / 2 + Fa / 2 + 3);
                  break;
                case 'top':
                  Sa = - (ib / 2 + Fa / 2 + 3);
                  break;
                case 'right':
                  Ga = Fa / 2 + 2 + Ha / 2;
                  break;
                case 'bottom':
                  o && 'column' == g ? 0 > S ? (Ga = - Ha / 2 - 8, aa = Q)  : Ga = Q + 6 + Ha / 2 - aa : (Sa = Fa / 2 + ib / 2, W.x = - (Ha / 2 + 2));
                  break;
                case 'middle':
                  'column' == g && (o ? (Ga = - (x - Q) /
                  2 - t, Math.abs(x - Q) < Ha && !a.showAllValueLabels && (W.remove(), a.set.exclude(W), W = null))  : (Sa = - (w - R) / 2 + 1 - I, Math.abs(w - R) < ib && !a.showAllValueLabels && (W.remove(), a.set.exclude(W), W = null)))
              }
              if (W) if (W.attr({
                x: aa + Ga,
                y: ba + Sa
              }), oa = W.getBBox(), o) {
                if (oa.y < n || oa.y + oa.height > n + l) W.remove(),
                a.set.exclude(W),
                W = null
            } else if (oa.x < k || oa.x + oa.width > k + j) W.remove(),
            a.set.exclude(W),
            W = null
          }
        }
      }
  }
}
if ('line' == g || 'step' == g || 'smoothedLine' == g) 'smoothedLine' == g ? a.drawSmoothedGraph(q, r, ka, la)  : a.drawLineGraph(q, r, ka, la),
K || a.launchAnimation()
},
createLabel: function (a, b, c) {
var d = this.chart,
e = this.color;
void 0 == e && (e = d.color);
var f = this.fontSize;
void 0 == f && (f = d.fontSize);
a = d.formatString(this.labelText, a, this);
a = AmCharts.cleanFromEmpty(a);
b = AmCharts.text(this.container, b, c, a, {
fill: e,
'font-family': d.fontFamily,
'font-size': f
});
this.set.push(b);
this.allBullets.push(b);
return b
},
setPositiveClipRect: function (a) {
a.attr({
'clip-rect': this.pmx + ',' + this.pmy + ',' + this.pmw + ',' + this.pmh
})
},
setNegativeClipRect: function (a) {
a.attr({
'clip-rect': this.nmx + ',' +
this.nmy + ',' + this.nmw + ',' + this.nmh
})
},
drawLineGraph: function (a, b, c, d) {
if (1 < a.length) {
var e = AmCharts.line(this.container, a, b, this.lineColor, this.lineAlpha, this.lineThickness, this.dashLength);
this.positiveObjectsToClip.push(e);
this.set.push(e);
void 0 != this.negativeLineColor && (e = AmCharts.line(this.container, a, b, this.negativeLineColor, this.lineAlpha, this.lineThickness, this.dashLength), this.negativeObjectsToClip.push(e), this.set.push(e));
if (void 0 != this.fillAlphas && 0 != this.fillAlphas) {
  var e = a.join(';').split(';'),
  f = b.join(';').split(';');
  'serial' == this.chartType && (0 < c.length ? (c.reverse(), d.reverse(), e = a.concat(c), f = b.concat(d))  : this.rotate ? (f.push(f[f.length - 1]), e.push(this.baseCoord), f.push(f[0]), e.push(this.baseCoord), f.push(f[0]), e.push(e[0]))  : (e.push(e[e.length - 1]), f.push(this.baseCoord), e.push(e[0]), f.push(this.baseCoord), e.push(a[0]), f.push(f[0])));
  a = AmCharts.polygon(this.container, e, f, this.fillColors, this.fillAlphas);
  this.set.push(a);
  this.positiveObjectsToClip.push(a);
  if (this.negativeFillColors || void 0 !=
  this.negativeLineColor) a = this.fillAlphas,
  this.negativeFillAlphas && (a = this.negativeFillAlphas),
  b = this.negativeLineColor,
  this.negativeFillColors && (b = this.negativeFillColors),
  e = AmCharts.polygon(this.container, e, f, b, a),
  this.set.push(e),
  this.negativeObjectsToClip.push(e)
}
}
},
drawSmoothedGraph: function (a, b) {
if (1 < a.length) {
var c = new AmCharts.Bezier(this.container, a, b, this.lineColor, this.lineAlpha, this.lineThickness, NaN, NaN, this.dashLength);
this.positiveObjectsToClip.push(c.path);
this.set.push(c.path);
void 0 !=
this.negativeLineColor && (c = new AmCharts.Bezier(this.container, a, b, this.negativeLineColor, this.lineAlpha, this.lineThickness, NaN, NaN, this.dashLength), this.set.push(c.path), this.negativeObjectsToClip.push(c.path));
if (0 < this.fillAlphas) {
  c = [
  ];
  this.rotate ? (c.push('L', this.baseCoord, b[b.length - 1]), c.push('L', this.baseCoord, b[0]))  : (c.push('L', a[a.length - 1], this.baseCoord), c.push('L', a[0], this.baseCoord));
  c.push('L', a[0], b[0]);
  var d = new AmCharts.Bezier(this.container, a, b, NaN, NaN, 0, this.fillColors, this.fillAlphas, this.dashLength, c);
  this.positiveObjectsToClip.push(d.path);
  this.set.push(d.path);
  if (this.negativeFillColors || void 0 != this.negativeLineColor) {
    d = this.fillAlphas;
    this.negativeFillAlphas && (d = this.negativeFillAlphas);
    var e = this.negativeLineColor;
    this.negativeFillColors && (e = this.negativeFillColors);
    c = new AmCharts.Bezier(this.container, a, b, NaN, NaN, 0, e, d, this.dashLength, c);
    this.negativeObjectsToClip.push(c.path);
    this.set.push(c.path)
  }
}
}
},
launchAnimation: function () {
var a = this;
if (0 < a.sDur && !a.animationPlayed) {
var b =
a.set;
b.attr({
  opacity: a.startAlpha
});
a.rotate ? b.translate('-1000,0')  : b.translate('0,-1000');
a.seqAn ? (b = setTimeout(function () {
  a.animateGraphs.call(a)
}, 1000 * a.index * a.sDur), a.timeOuts.push(b))  : a.animateGraphs()
}
},
animateGraphs: function () {
if (0 < this.set.length) {
var a = 0,
b = 0;
this.rotate ? a = 1000 : b = 1000;
this.set.animate({
  opacity: 1,
  transform: '...t' + a + ',' + b
}, 1000 * this.sDur, this.sEff)
}
},
animate: function (a, b, c, d) {
var e = this.animationArray;
!a && 0 < e.length && (a = e[0].obj, b = e[0].fh, c = e[0].ip, d = e[0].fp, e.shift());
a.show();
this.rotate ? 0 < b ? (a.attr({
'fill-opacity': this.startAlpha,
width: 1
}), a.animate({
'fill-opacity': this.fillAlphas,
width: Math.abs(b)
}, 1000 * this.sDur, this.sEff))  : 0 > b && (a.attr({
'fill-opacity': this.startAlpha,
width: 1,
x: c
}), a.animate({
'fill-opacity': this.fillAlphas,
width: Math.abs(b),
x: d
}, 1000 * this.sDur, this.sEff))  : 0 < b ? (a.attr({
'fill-opacity': this.startAlpha,
height: 0.1
}), a.animate({
'fill-opacity': this.fillAlphas,
height: Math.abs(b)
}, 1000 * this.sDur, this.sEff))  : 0 > b && (a.attr({
'fill-opacity': this.startAlpha,
height: 0.1,
y: c
}), a.animate({
'fill-opacity': this.fillAlphas,
height: Math.abs(b),
y: d
}, 1000 * this.sDur, this.sEff))
},
legendKeyColor: function () {
var a = this.legendColor,
b = this.lineAlpha;
void 0 == a && (a = this.lineColor, 0 == b && (b = this.fillColors) && (a = 'object' == typeof b ? b[0] : b));
return a
},
legendKeyAlpha: function () {
var a = this.legendAlpha;
void 0 == a && (a = this.lineAlpha, 0 == a && this.fillAlphas && (a = this.fillAlphas), 0 == a && (a = this.bulletAlpha), 0 == a && (a = 1));
return a
},
createBullet: function (a, b, c) {
var d = '';
a.url && (d = 'pointer');
var e = this.bulletOffset,
f = this.bulletSize;
isNaN(a.bulletSize) || (f = a.bulletSize);
if (!isNaN(this.maxValue)) {
var g = a.values.value;
isNaN(g) || (f = g / this.maxValue * this.maxBulletSize)
}
f < this.minBulletSize && (f = this.minBulletSize);
this.rotate ? b += e : c -= e;
var h;
if ('none' != this.bullet || a.bullet) {
var j = this.bulletColor;
a.isNegative && void 0 != this.bulletColorNegative && (j = this.bulletColorNegative);
void 0 != a.color && (j = a.color);
e = this.bullet;
a.bullet && (e = a.bullet);
var g = this.bulletBorderThickness,
l = this.bulletBorderColor,
k = this.bulletBorderAlpha,
n = this.bulletAlpha;
switch (e) {
  case 'round':
    h = AmCharts.circle(this.container, f / 2, j, n, g, l, k);
    break;
  case 'square':
    h = AmCharts.rect(this.container, f, f, j, n, g, l, k);
    b -= f / 2;
    c -= f / 2;
    break;
  case 'triangleUp':
    h = AmCharts.triangle(this.container, f, 0, j, n, g, l, k);
    break;
  case 'triangleDown':
    h = AmCharts.triangle(this.container, f, 180, j, n, g, l, k);
    break;
  case 'triangleLeft':
    h = AmCharts.triangle(this.container, f, 270, j, n, g, l, k);
    break;
  case 'triangleRight':
    h = AmCharts.triangle(this.container, f, 90, j, n, g, l, k);
    break;
  case 'bubble':
    h = AmCharts.circle(this.container, f / 2, j, n, g, l, k, !0)
}
}
if (this.customBullet || a.customBullet) e = this.customBullet,
a.customBullet && (e = a.customBullet),
e && (h && h.remove(), 'function' == typeof e ? (h = new e, h.chart = this.chart, a.bulletConfig && (h.availableSpace = c, h.graph = this, a.bulletConfig.minCoord = this.minCoord - c, h.bulletConfig = a.bulletConfig), h.write(this.container), h = h.set)  : (this.chart.path && (e = this.chart.path + e), h = this.container.image(e, 0, 0, f, f).attr({
preserveAspectRatio: !0
}), this.centerCustomBullets && (b -= f / 2, c -= f / 2)));
if (h) {
h.attr({
  cursor: d
});
this.allBullets.push(h);
if ('serial' == this.chartType && (b < this.x || b > this.x + this.width || c < this.y - f / 2 || c > this.y + this.height)) h.remove(),
h = null;
h && ('xy' == this.chartType && this.setPositiveClipRect(h), this.set.push(h), h.transform('...T' + b + ',' + c), h.dItem = a, this.objectsToAddListeners.push(h))
}
return f
},
showBullets: function () {
for (var a = 0; a < this.allBullets.length; a++) this.allBullets[a].show()
},
hideBullets: function () {
for (var a = 0; a < this.allBullets.length; a++) this.allBullets[a].hide()
},
addHoverListeners: function (a) {
var b =
this;
a.mouseover(function () {
b.handleRollOver.call(b, this.dItem)
}).mouseout(function () {
b.handleRollOut.call(b, this.dItem)
})
},
addClickListeners: function (a) {
var b = this;
b.chart.touchEventsEnabled && a.touchstart(function () {
b.handleRollOver(this.dItem)
}).touchend(function () {
b.handleClick(this.dItem)
});
a.click(function () {
b.handleClick.call(b, this.dItem)
}).dblclick(function () {
b.handleDoubleClick.call(b, this.dItem)
})
},
handleRollOver: function (a) {
if (a) {
var b = this.chart,
c = {
  type: 'rollOverGraphItem',
  item: a,
  index: a.index,
  graph: this
};
this.fire('rollOverGraphItem', c);
b.fire('rollOverGraphItem', c);
clearTimeout(b.hoverInt);
c = !0;
b.chartCursor && 'serial' == this.chartType && (c = !1, b.chartCursor.valueBalloonsEnabled || (c = !0));
c && this.showBalloon && (c = b.formatString(this.balloonText, a, this), c = AmCharts.cleanFromEmpty(c), a = b.getBalloonColor(this, a), b.balloon.showBullet = !1, b.balloon.pointerOrientation = 'vertical', b.showBalloon(c, a, !0))
}
},
handleRollOut: function (a) {
this.chart.hideBalloon();
a && (a = {
type: 'rollOutGraphItem',
item: a,
index: a.index,
graph: this
}, this.fire('rollOutGraphItem', a), this.chart.fire('rollOutGraphItem', a))
},
handleClick: function (a) {
if (a) {
var b = {
  type: 'clickGraphItem',
  item: a,
  index: a.index,
  graph: this
};
this.fire('clickGraphItem', b);
this.chart.fire('clickGraphItem', b);
a = a.url;
b = this.urlTarget;
a && ('_self' == b || !b ? window.location.href = a : (b = document.getElementsByName(b) [0]) ? b.src = a : window.open(a))
}
},
handleDoubleClick: function (a) {
a && (a = {
type: 'doubleClickGraphItem',
item: a,
index: a.index,
graph: this
}, this.fire('doubleClickGraphItem', a), this.chart.fire('doubleClickGraphItem', a))
},
zoom: function (a, b) {
this.start = a;
this.end = b;
this.draw()
},
changeOpacity: function (a) {
this.set && this.set.attr({
opacity: a
})
},
destroy: function () {
AmCharts.removeSet(this.set);
var a = this.timeOuts;
if (a) for (var b = 0; b < a.length; b++) clearTimeout(a[b]);
this.timeOuts = [
]
}
});
AmCharts.ChartCursor = AmCharts.Class({
construct: function () {
this.createEvents('changed', 'zoomed', 'onHideCursor', 'draw');
this.enabled = !0;
this.cursorAlpha = 1;
this.selectionAlpha = 0.2;
this.cursorColor = '#CC0000';
this.categoryBalloonAlpha = 1;
this.color = '#FFFFFF';
this.type = 'cursor';
this.zoomed = !1;
this.zoomable = !0;
this.pan = !1;
this.animate = !0;
this.categoryBalloonDateFormat = 'MMM DD, YYYY';
this.categoryBalloonEnabled = this.valueBalloonsEnabled = !0;
this.rolledOver = !1;
this.cursorPosition = 'middle';
this.bulletsEnabled =
this.skipZoomDispatch = !1;
this.bulletSize = 8;
this.oneBalloonOnly = !1
},
draw: function () {
var a = this;
a.destroy();
var b = a.chart,
c = b.container;
a.rotate = b.rotate;
a.container = c;
a.set = c.set();
a.allBullets = c.set();
c = new AmCharts.AmBalloon;
a.categoryBalloon = c;
c.cornerRadius = 0;
c.borderWidth = 1;
c.borderAlpha = 0;
c.chart = b;
b = a.categoryBalloonColor;
void 0 == b && (b = a.cursorColor);
c.fillColor = b;
c.fillAlpha = a.categoryBalloonAlpha;
c.borderColor = b;
c.color = a.color;
a.rotate && (c.pointerOrientation = 'horizontal');
'cursor' == a.type ?
a.createCursor()  : a.createCrosshair();
a.interval = setInterval(function () {
a.detectMovement.call(a)
}, 40)
},
updateData: function () {
var a = this.chart.chartData;
if ((this.data = a) && 0 < a.length && a) this.firstTime = a[0].time,
this.lastTime = a[a.length - 1].time
},
createCursor: function () {
var a = this.chart,
b = this.cursorAlpha,
c = a.categoryAxis,
d = c.position,
e = c.inside,
f = c.axisThickness,
g = this.categoryBalloon,
h,
j,
l = this.x,
k = this.y,
n = a.dx,
o = a.dy,
m = this.width,
p = this.height,
a = a.rotate,
s = c.tickLength;
g.pointerWidth = s;
a ? (h = [
0,
m,
m +
n
], j = [
0,
0,
o
])  : (h = [
n,
0,
0
], j = [
o,
0,
p
]);
this.line = b = AmCharts.line(this.container, h, j, this.cursorColor, b, 1);
b.translate(l + ',' + k);
this.set.push(b);
a ? (e && (g.pointerWidth = 0), 'right' == d ? e ? g.setBounds(l, k + o, l + m + n, k + p + o)  : g.setBounds(l + m + n + f, k + o, l + m + 1000, k + p + o)  : e ? g.setBounds(l, k, m + l, p + k)  : g.setBounds( - 1000, - 1000, l - s - f, k + p + 15))  : (g.maxWidth = m, c.parseDates && (s = 0, g.pointerWidth = 0), 'top' == d ? e ? g.setBounds(l + n, k + o, m + n + l, p + k)  : g.setBounds(l + n, - 1000, m + n + l, k + o - s - f)  : e ? g.setBounds(l, k, m + l, p + k - s)  : g.setBounds(l, k + p + s + f - 1, l + m, k +
p + s + f));
this.hideCursor()
},
createCrosshair: function () {
var a = this.cursorAlpha,
b = this.container,
c = AmCharts.line(b, [
0,
0
], [
0,
this.height
], this.cursorColor, a, 1),
a = AmCharts.line(b, [
0,
this.width
], [
0,
0
], this.cursorColor, a, 1);
c.translate(this.x + ',' + this.y);
a.translate(this.x + ',' + this.y);
this.set.push(c);
this.set.push(a);
this.vLine = c;
this.hLine = a;
this.selection = AmCharts.rect(b, 1, 1, [
this.cursorColor
], [
this.selectionAlpha
]);
this.selection.hide();
this.hideCursor()
},
detectMovement: function () {
var a = this.chart;
if (a.mouseIsOver) {
var b =
a.mouseX,
c = a.mouseY,
d = this.x,
e = this.y;
b > d && b < d + this.width && c > e && c < this.height + e ? (this.drawing ? this.rolledOver || a.setMouseCursor('crosshair')  : this.pan && (this.rolledOver || a.setMouseCursor('move')), this.rolledOver = !0, this.setPosition())  : this.rolledOver && (this.handleMouseOut(), this.rolledOver = !1)
} else this.rolledOver && (this.handleMouseOut(), this.rolledOver = !1)
},
getMousePosition: function () {
var a,
b = this.x,
c = this.y,
d = this.width,
e = this.height;
a = this.chart;
this.rotate ? (a = a.mouseY, a < c && (a = c), a > e + c && (a = e + c))  : (a = a.mouseX, a < b && (a = b), a > d + b && (a = d + b));
return a
},
updateCrosshair: function () {
var a = this.chart.mouseX,
b = this.chart.mouseY,
c = this.x,
d = this.y,
e = this.vLine,
f = this.hLine,
a = AmCharts.fitToBounds(a, c, c + this.width),
b = AmCharts.fitToBounds(b, d, d + this.height);
0 < this.cursorAlpha && (c = e.getBBox(), d = f.getBBox(), e.show(), f.show(), e.translate(Math.round(a - c.x) + ',0'), f.translate('0,' + Math.round(b - d.y)));
this.zooming && this.updateSelectionSize(a, b);
!this.chart.mouseIsOver && !this.zooming && this.hideCursor()
},
updateSelectionSize: function (a, b) {
AmCharts.removeObject(this.selection, this.set);
var c = this.x,
d = this.y,
e = this.width,
f = this.height,
g = this.selectionPosX,
h = this.selectionPosY;
isNaN(a) || (g > a && (c = a, e = g - a), g < a && (c = g, e = a - g), g == a && (c = a, e = 0));
isNaN(b) || (d = this.y, h > b && (d = b, f = h - b), h < b && (d = h, f = b - h), h == b && (d = b, f = 0));
0 < e && 0 < f && (this.selection = AmCharts.rect(this.container, e, f, [
this.cursorColor
], [
this.selectionAlpha
]), this.selection.translate(c + ',' + d), this.set.push(this.selection))
},
arrangeBalloons: function () {
var a = this.x,
b = this.y,
c = this.valueBalloons,
d = b + this.height;
c.sort(this.compareY);
for (var e = 0; e < c.length; e++) {
var f = c[e].balloon;
f.setBounds(a, b, a + this.width, d);
f.draw();
d = f.yPos - 3
}
this.arrangeBalloons2()
},
compareY: function (a, b) {
return a.yy < b.yy ? 1 : - 1
},
arrangeBalloons2: function () {
var a = this.valueBalloons;
a.reverse();
for (var b, c, d = 0; d < a.length; d++) {
var e = a[d].balloon;
b = e.bottom;
var f = e.bottom - e.yPos;
0 < d && b - f < c + 3 && (e.setBounds(this.x, c + 3, this.x + this.width, c + f + 3), e.draw());
e.set && e.set.show();
c = e.bottom
}
},
showBullets: function () {
AmCharts.removeSet(this.allBullets);
for (var a = this.chart.graphs, b = 0; b < a.length; b++) {
var c = a[b];
if (c.showBalloon && !c.hidden && c.balloonText) {
  var d = this.data[this.index].axes[c.valueAxis.id].graphs[c.id],
  e = d.y;
  if (!isNaN(e)) {
    var f,
    g;
    f = d.x;
    this.rotate ? (g = e, e = f)  : g = f;
    c = AmCharts.circle(this.container, this.bulletSize / 2, this.chart.getBalloonColor(c, d), c.cursorBulletAlpha);
    c.translate(g + ',' + e);
    this.allBullets.push(c)
  }
}
}
},
destroy: function () {
this.clear();
this.selection = null;
var a = this.categoryBalloon;
a && a.destroy();
this.destroyValueBalloons();
AmCharts.removeSet(this.set);
AmCharts.removeSet(this.allBullets)
},
clear: function () {
clearInterval(this.interval)
},
destroyValueBalloons: function () {
var a = this.valueBalloons;
if (a) for (var b = 0; b < a.length; b++) a[b].balloon.destroy()
},
zoom: function (a, b, c, d) {
var e = this.chart;
this.destroyValueBalloons();
this.zooming = !1;
var f;
this.rotate ? this.selectionPosY = f = e.mouseY : this.selectionPosX = f = e.mouseX;
this.start = a;
this.end = b;
this.startTime = c;
this.endTime = d;
this.zoomed = !0;
var g = e.categoryAxis,
e = this.rotate;
f = this.width;
var h = this.height;
g.parseDates &&
!g.equalSpacing ? (a = d - c + g.minDuration(), a = e ? h / a : f / a)  : a = e ? h / (b - a)  : f / (b - a);
this.stepWidth = a;
this.setPosition();
this.hideCursor()
},
hideCursor: function (a) {
void 0 == a && (a = !0);
this.set && this.set.hide();
this.categoryBalloon && this.categoryBalloon.hide();
this.destroyValueBalloons();
AmCharts.removeSet(this.allBullets);
this.previousIndex = NaN;
a && this.fire('onHideCursor', {
type: 'onHideCursor',
chart: this.chart,
target: this
});
this.chart.setMouseCursor('auto')
},
setPosition: function (a, b) {
void 0 == b && (b = !0);
if ('cursor' ==
this.type) {
if (this.data && 0 < this.data.length) {
  a || (a = this.getMousePosition());
  if ((a != this.previousMousePosition || !0 == this.zoomed || this.oneBalloonOnly) && !isNaN(a)) {
    var c = this.chart.categoryAxis.xToIndex(a);
    if (c != this.previousIndex || this.zoomed || 'mouse' == this.cursorPosition || this.oneBalloonOnly) this.updateCursor(c, b),
    this.zoomed = !1
  }
  this.previousMousePosition = a
}
} else this.updateCrosshair()
},
updateCursor: function (a, b) {
var c = this.chart,
d = c.mouseX,
e = c.mouseY;
this.drawingNow && (AmCharts.removeObject(this.drawingLine), this.drawingLine = AmCharts.line(this.container, [
this.drawStartX,
d
], [
this.drawStartY,
e
], this.cursorColor, 1, 1));
if (this.enabled) {
void 0 == b && (b = !0);
this.index = a;
var f = c.categoryAxis,
g = this.x,
h = this.y,
j = c.dx,
l = c.dy,
k = this.width,
n = this.height,
o = this.data[a],
m = o.x[f.id],
p = c.rotate,
s = f.inside,
q = this.stepWidth,
r = this.categoryBalloon,
v = this.firstTime,
y = this.lastTime,
B = this.cursorPosition,
J = f.position,
t = this.zooming,
I = this.panning,
H = c.graphs,
D = c.touchEventsEnabled,
T = f.axisThickness;
if (c.mouseIsOver || t || I || this.forceShow) if (this.forceShow =
!1, I) {
  k = this.panClickPos;
  c = this.panClickEndTime;
  t = this.panClickStartTime;
  g = this.panClickEnd;
  h = this.panClickStart;
  d = (p ? k - e : k - d) / q;
  if (!f.parseDates || f.equalSpacing) d = Math.round(d);
  0 != d && (f.parseDates && !f.equalSpacing ? (c + d > y && (d = y - c), t + d < v && (d = v - t), k = {
    type: 'zoomed'
  }, k.start = t + d, k.end = c + d, k.target = this, this.fire('zoomed', k))  : g + d >= this.data.length || 0 > h + d || (k = {
    type: 'zoomed'
  }, k.start = h + d, k.end = g + d, k.target = this, this.fire(k.type, k)))
} else {
  'start' == B && (m -= f.cellWidth / 2);
  'mouse' == B && (m = p ? e - 2 : d - 2);
  if (p) {
    if (m <
    h) if (t) m = h;
     else {
      this.hideCursor();
      return
    }
    if (m > n + 1 + h) if (t) m = n + 1 + h;
     else {
      this.hideCursor();
      return
    }
  } else {
    if (m < g) if (t) m = g;
     else {
      this.hideCursor();
      return
    }
    if (m > k + g) if (t) m = k + g;
     else {
      this.hideCursor();
      return
    }
  }
  0 < this.cursorAlpha && (v = this.line, y = v.getBBox(), p ? v.translate('0,' + Math.round(m - y.y + l))  : v.translate(Math.round(m - y.x) + ',0'), v.show());
  this.linePos = p ? m + l : m;
  t && (p ? this.updateSelectionSize(NaN, m)  : this.updateSelectionSize(m, NaN));
  v = !0;
  D && t && (v = !1);
  this.categoryBalloonEnabled && v ? (p ? (s && ('right' == J ? r.setBounds(g, h + l, g + k + j, g + m + l)  : r.setBounds(g, h + l, g + k + j, g + m)), 'right' == J ? s ? r.setPosition(g + k + j, m + l)  : r.setPosition(g + k + j + T, m + l)  : s ? r.setPosition(g, m)  : r.setPosition(g - T, m))  : 'top' == J ? s ? r.setPosition(m + j, h + l)  : r.setPosition(m + j, h + l - T + 1)  : s ? r.setPosition(m, h + n)  : r.setPosition(m, h + n + T - 1), f.parseDates ? (f = AmCharts.formatDate(o.category, this.categoryBalloonDateFormat), - 1 != f.indexOf('fff') && (f = AmCharts.formatMilliseconds(f, o.category)), r.showBalloon(f))  : r.showBalloon(o.category))  : r.hide();
  H && this.bulletsEnabled && this.showBullets();
  this.destroyValueBalloons();
  if (H && this.valueBalloonsEnabled && v && c.balloon.enabled) {
    this.valueBalloons = f = [
    ];
    if (this.oneBalloonOnly) for (var j = Infinity, C, v = 0; v < H.length; v++) q = H[v],
    q.showBalloon && !q.hidden && q.balloonText && (r = o.axes[q.valueAxis.id].graphs[q.id], y = r.y, isNaN(y) || (p ? Math.abs(d - y) < j && (j = Math.abs(d - y), C = q)  : Math.abs(e - y) < j && (j = Math.abs(e - y), C = q)));
    for (v = 0; v < H.length; v++) if (q = H[v], !(this.oneBalloonOnly && q != C) && q.showBalloon && !q.hidden && q.balloonText && (r = o.axes[q.valueAxis.id].graphs[q.id], y =
    r.y, !isNaN(y))) {
      l = r.x;
      m = !0;
      if (p) {
        if (j = y, l < h || l > h + n) m = !1
      } else if (j = l, l = y, j < g || j > g + k) m = !1;
      m && (s = c.getBalloonColor(q, r), m = new AmCharts.AmBalloon, m.chart = c, AmCharts.copyProperties(c.balloon, m), m.setBounds(g, h, g + k, h + n), m.pointerOrientation = 'horizontal', m.changeColor(s), void 0 != q.balloonAlpha && (m.fillAlpha = q.balloonAlpha), void 0 != q.balloonTextColor && (m.color = q.balloonTextColor), m.setPosition(j, l), q = c.formatString(q.balloonText, r, q), '' != q && m.showBalloon(q), !p && m.set && m.set.hide(), f.push({
        yy: y,
        balloon: m
      }))
    }
    p ||
    this.arrangeBalloons()
  }
  b ? (k = {
    type: 'changed'
  }, k.index = a, k.zooming = t, k.position = p ? e : d, k.target = this, c.fire('changed', k), this.fire('changed', k), this.skipZoomDispatch = !1)  : (this.skipZoomDispatch = !0, c.updateLegendValues(a));
  this.previousIndex = a
}
} else this.hideCursor()
},
enableDrawing: function (a) {
this.enabled = !a;
this.hideCursor();
this.rolledOver = !1;
this.drawing = a
},
isZooming: function (a) {
a && a != this.zooming && this.handleMouseDown('fake');
!a && a != this.zooming && this.handleMouseUp()
},
handleMouseOut: function () {
this.enabled && (this.zooming ? this.setPosition()  : (this.index = void 0, this.fire('changed', {
type: 'changed',
index: void 0,
target: this
}), this.hideCursor()))
},
handleReleaseOutside: function () {
this.handleMouseUp()
},
handleMouseUp: function () {
var a = this.chart,
b = a.mouseX,
a = a.mouseY;
if (this.drawingNow) {
this.drawingNow = !1;
AmCharts.removeObject(this.drawingLine);
var c = this.drawStartX,
d = this.drawStartY;
if (2 < Math.abs(c - b) || 2 < Math.abs(d - a)) c = {
  type: 'draw',
  initialX: c,
  initialY: d,
  finalX: b,
  finalY: a
},
this.fire(c.type, c)
}
this.enabled && (this.pan ?
this.rolledOver = !1 : this.zoomable && this.zooming && ('cursor' == this.type ? (this.rotate ? this.selectionPosY = b = a : this.selectionPosX = b, 2 > Math.abs(b - this.initialMouse) && this.fromIndex == this.index || (b = {
type: 'zoomed'
}, this.index < this.fromIndex ? (b.end = this.fromIndex, b.start = this.index)  : (b.end = this.index, b.start = this.fromIndex), a = this.chart.categoryAxis, a.parseDates && !a.equalSpacing && (b.start = this.data[b.start].time, b.end = this.data[b.end].time), AmCharts.removeSet(this.allBullets), this.skipZoomDispatch || (b.target =
this, this.fire('zoomed', b))))  : 3 > Math.abs(b - this.initialMouseX) && 3 > Math.abs(a - this.initialMouseY) || (a = this.selection.getBBox(), b = {
type: 'zoomed'
}, b.selectionHeight = a.height, b.selectionWidth = a.width, b.selectionY = a.y - this.y, b.selectionX = a.x - this.x, this.skipZoomDispatch || (b.target = this, this.fire('zoomed', b))), AmCharts.removeObject(this.selection, this.set)), this.panning = this.zooming = this.skipZoomDispatch = !1)
},
handleMouseDown: function (a) {
if (this.zoomable || this.pan || this.drawing) {
var b = this.rotate,
c = this.chart,
d = c.mouseX,
e = c.mouseY;
if (d > this.x && d < this.x + this.width && e > this.y && e < this.height + this.y || 'fake' == a) this.setPosition(),
this.drawing ? (this.drawStartY = e, this.drawStartX = d, this.drawingNow = !0)  : this.pan ? (this.zoomable = !1, c.setMouseCursor('move'), this.panning = !0, this.hideCursor(!0), this.panClickPos = b ? e : d, this.panClickStart = this.start, this.panClickEnd = this.end, this.panClickStartTime = this.startTime, this.panClickEndTime = this.endTime)  : this.zoomable && ('cursor' == this.type ? (this.fromIndex = this.index, b ? (this.initialMouse =
e, this.selectionPosY = this.linePos)  : (this.initialMouse = d, this.selectionPosX = this.linePos))  : (this.initialMouseX = d, this.initialMouseY = e, this.selectionPosX = d, this.selectionPosY = e), this.zooming = !0)
}
}
});
AmCharts.SimpleChartScrollbar = AmCharts.Class({
construct: function () {
this.createEvents('zoomed');
this.backgroundColor = '#D4D4D4';
this.backgroundAlpha = 1;
this.selectedBackgroundColor = '#EFEFEF';
this.selectedBackgroundAlpha = 1;
this.scrollDuration = 2;
this.resizeEnabled = !0;
this.hideResizeGrips = !1;
this.scrollbarHeight = 20;
this.updateOnReleaseOnly = !1;
this.dragIconWidth = 11;
this.dragIconHeight = 18
},
draw: function () {
var a = this;
a.destroy();
a.interval = setInterval(function () {
a.updateScrollbar.call(a)
}, 40);
var b = a.chart.container,
c = a.rotate,
d = a.chart,
e = b.set();
a.set = e;
d.touchEventsEnabled && (a.updateOnReleaseOnly = !0);
var f,
g;
c ? (f = a.scrollbarHeight, g = a.chart.plotAreaHeight)  : (g = a.scrollbarHeight, f = a.chart.plotAreaWidth);
a.width = f;
if ((a.height = g) && f) {
var h = AmCharts.rect(b, f, g, [
  a.backgroundColor
], [
  a.backgroundAlpha
]);
e.push(h);
h = AmCharts.rect(b, f, g, [
  0
], [
  0
]);
e.push(h);
a.invisibleBg = h;
d.touchEventsEnabled && h.touchend(function () {
  a.handleBackgroundClick()
});
h.click(function () {
  a.handleBackgroundClick()
}).mouseover(function () {
  a.handleMouseOver()
}).mouseout(function () {
  a.handleMouseOut()
});
h = AmCharts.rect(b, f, g, [
  a.selectedBackgroundColor
], [
  a.selectedBackgroundAlpha
]);
a.selectedBG = h;
e.push(h);
f = AmCharts.rect(b, f, g, [
  '#000'
], [
  0
]);
a.dragger = f;
e.push(f);
d.touchEventsEnabled && f.touchstart(function (b) {
  a.handleDragStart(b)
}).touchend(function () {
  a.handleDragStop()
});
f.mousedown(function (b) {
  a.handleDragStart(b)
}).mouseup(function () {
  a.handleDragStop()
}).mouseover(function () {
  a.handleDraggerOver()
}).mouseout(function () {
  a.handleMouseOut()
});
c ? (h = d.pathToImages + 'dragIconH.gif', g = a.dragIconWidth, f =
a.dragIconHeight)  : (h = d.pathToImages + 'dragIcon.gif', f = a.dragIconWidth, g = a.dragIconHeight);
c = b.image(h, 0, 0, f, g);
b = b.image(h, 0, 0, f, g);
a.dragIconLeft = c;
e.push(a.dragIconLeft);
a.dragIconRight = b;
e.push(b);
c.mousedown(function () {
  a.handleLeftIconDragStart()
}).mouseup(function () {
  a.handleLeftIconDragStop()
}).mouseover(function () {
  a.handleIconRollOver()
}).mouseout(function () {
  a.handleIconRollOut()
});
b.mousedown(function () {
  a.handleRightIconDragStart()
}).mouseup(function () {
  a.handleRightIconDragStop()
}).mouseover(function () {
  a.handleIconRollOver()
}).mouseout(function () {
  a.handleIconRollOut()
});
0 < d.chartData.length ? e.show()  : e.hide();
a.hideResizeGrips && (c.hide(), b.hide())
}
e.attr({
x: a.x,
y: a.y
});
a.clipDragger(!1);
a.updateDragIconPositions()
},
updateScrollbarSize: function (a, b) {
var c = this.dragger,
d,
e,
f,
g;
this.rotate ? (d = this.x, e = a, f = this.width, g = b - a, c.attr('height', b - a), c.attr('y', e))  : (d = a, e = this.y, f = b - a, g = this.height, c.attr('width', b - a), c.attr('x', d));
this.clipAndUpdate(d, e, f, g)
},
updateScrollbar: function () {
var a,
b = !1,
c,
d,
e = this.dragger,
f = e.getBBox();
c = f.x;
d = f.y;
var g = f.width,
f = f.height,
h = this.rotate,
j = this.chart,
l = this.width,
k = this.height,
n = j.mouseX,
o = j.mouseY,
m = this.x,
p = this.y;
a = this.initialMouseCoordinate;
j.mouseIsOver && (this.dragging && (j = this.initialDragCoordinate, h ? (a = j + (o - a), a < p && (a = p), j = p + k - f, a > j && (a = j), e.attr({
y: a
}))  : (a = j + (n - a), a < m && (a = m), j = m + l - g, a > j && (a = j), e.attr({
x: a
}))), this.resizingRight && (h ? (a = o - d, a + d > k + p && (a = k - d + p), 0 > a ? (this.resizingRight = !1, b = this.resizingLeft = !0)  : (0 == a && (a = 0.1), e.attr('height', a)))  : (a = n - c, a + c > l + m && (a = l - c + m), 0 > a ? (this.resizingRight = !1, b = this.resizingLeft = !0)  : (0 ==
a && (a = 0.1), e.attr('width', a)))), this.resizingLeft && (h ? (c = d, d = o, d < p && (d = p), d > k + p && (d = k + p), a = !0 == b ? c - d : f + c - d, 0 > a ? (this.resizingRight = !0, this.resizingLeft = !1, e.attr('y', c + f))  : (0 == a && (a = 0.1), e.attr('y', d), e.attr('height', a)))  : (d = n, d < m && (d = m), d > l + m && (d = l + m), a = !0 == b ? c - d : g + c - d, 0 > a ? (this.resizingRight = !0, this.resizingLeft = !1, e.attr('x', c + g))  : (0 == a && (a = 0.1), e.attr('x', d), e.attr('width', a)))), this.clipDragger(!0))
},
clipDragger: function (a) {
var b = this.dragger.getBBox(),
c = b.x,
d = b.y,
e = b.width,
b = b.height,
f = !1;
if (this.rotate) {
if (this.clipY !=
d || this.clipH != b) f = !0
} else if (this.clipX != c || this.clipW != e) f = !0;
f && (this.clipAndUpdate(c, d, e, b), a && (this.updateOnReleaseOnly || this.dispatchScrollbarEvent()))
},
maskGraphs: function () {
},
clipAndUpdate: function (a, b, c, d) {
this.clipX = a;
this.clipY = b;
this.clipW = c;
this.clipH = d;
this.clipRect = a = a + ',' + b + ',' + c + ',' + d;
this.selectedBG.attr({
'clip-rect': a
});
this.updateDragIconPositions();
this.maskGraphs(a)
},
dispatchScrollbarEvent: function () {
if (this.skipEvent) this.skipEvent = !1;
 else {
this.chart.hideBalloon();
var a = this.dragger.getBBox(),
b = a.x - this.x,
c = a.y - this.y,
d = a.width,
a = a.height;
this.rotate ? (b = c, d = this.height / a)  : d = this.width / d;
d = {
  type: 'zoomed',
  position: b,
  multiplyer: d
};
this.fire(d.type, d)
}
},
updateDragIconPositions: function () {
var a = this.dragger.getBBox(),
b = a.x,
c = a.y,
d = this.dragIconLeft,
e = this.dragIconRight,
f,
g,
h = this.scrollbarHeight;
this.rotate ? (f = this.dragIconWidth, g = this.dragIconHeight, d.attr({
y: Math.round(c - f / 2),
x: b + (h - g) / 2
}), e.attr({
y: Math.round(c + a.height - f / 2),
x: b + (h - g) / 2
}))  : (f = this.dragIconHeight, g = this.dragIconWidth, d.attr({
x: Math.round(b -
g / 2),
y: c + (h - f) / 2
}), e.attr({
x: Math.round(b - g / 2 + a.width),
y: c + (h - f) / 2
}))
},
showDragIcons: function () {
this.resizeEnabled && (this.dragIconLeft.show(), this.dragIconRight.show())
},
hideDragIcons: function () {
!this.resizingLeft && !this.resizingRight && !this.dragging && (this.hideResizeGrips && (this.dragIconLeft.hide(), this.dragIconRight.hide()), this.removeCursors())
},
removeCursors: function () {
this.chart.setMouseCursor('auto')
},
relativeZoom: function (a, b) {
this.multiplyer = a;
var c = this.position = b,
d;
this.rotate ? (c += this.y, d = c + this.height / a)  : (c += this.x, d = c + this.width / a);
this.updateScrollbarSize(c, d)
},
destroy: function () {
this.clear();
AmCharts.removeSet(this.set)
},
clear: function () {
clearInterval(this.interval)
},
handleDragStart: function (a) {
this.dragger.stop();
a && a.preventDefault();
this.removeCursors();
this.dragging = !0;
a = this.dragger.getBBox();
this.rotate ? (this.initialDragCoordinate = a.y, this.initialMouseCoordinate = this.chart.mouseY)  : (this.initialDragCoordinate = a.x, this.initialMouseCoordinate = this.chart.mouseX)
},
handleDragStop: function () {
this.updateOnReleaseOnly && (this.updateScrollbar(), this.skipEvent = !1, this.dispatchScrollbarEvent());
this.dragging = !1;
this.mouseIsOver && this.removeCursors();
this.updateScrollbar()
},
handleDraggerOver: function () {
this.handleMouseOver()
},
handleLeftIconDragStart: function () {
this.dragger.stop();
this.resizingLeft = !0
},
handleLeftIconDragStop: function () {
this.resizingLeft = !1;
this.mouseIsOver || this.removeCursors();
this.updateOnRelease()
},
handleRightIconDragStart: function () {
this.dragger.stop();
this.resizingRight = !0
},
handleRightIconDragStop: function () {
this.resizingRight =
!1;
this.mouseIsOver || this.removeCursors();
this.updateOnRelease()
},
handleIconRollOut: function () {
this.removeCursors()
},
handleIconRollOver: function () {
this.rotate ? this.chart.setMouseCursor('n-resize')  : this.chart.setMouseCursor('e-resize');
this.handleMouseOver()
},
handleBackgroundClick: function () {
if (!this.resizingRight && !this.resizingLeft) {
this.zooming = !0;
var a,
b,
c = this.scrollDuration,
d = this.dragger;
a = this.dragger.getBBox();
var e = a.height,
f = a.width;
b = this.chart;
var g = this.y,
h = this.x,
j = this.rotate;
j ? (a =
'y', b = b.mouseY - e / 2, b = AmCharts.fitToBounds(b, g, g + this.height - e))  : (a = 'x', b = b.mouseX - f / 2, b = AmCharts.fitToBounds(b, h, h + this.width - f));
this.updateOnReleaseOnly ? (this.skipEvent = !1, d.attr(a, b), this.dispatchScrollbarEvent())  : j ? d.animate({
  y: b
}, 1000 * c, '>')  : d.animate({
  x: b
}, 1000 * c, '>')
}
},
updateOnRelease: function () {
this.updateOnReleaseOnly && (this.updateScrollbar(), this.skipEvent = !1, this.dispatchScrollbarEvent())
},
handleReleaseOutside: function () {
if (this.set) {
if (this.resizingLeft || this.resizingRight || this.dragging) this.updateOnRelease(),
this.removeCursors();
this.mouseIsOver = this.dragging = this.resizingRight = this.resizingLeft = !1;
this.hideResizeGrips && (this.dragIconLeft.hide(), this.dragIconRight.hide());
this.updateScrollbar()
}
},
handleMouseOver: function () {
this.mouseIsOver = !0;
this.showDragIcons()
},
handleMouseOut: function () {
this.mouseIsOver = !1;
this.hideDragIcons()
}
});
AmCharts.ChartScrollbar = AmCharts.Class({
inherits: AmCharts.SimpleChartScrollbar,
construct: function () {
AmCharts.ChartScrollbar.base.construct.call(this);
this.graphLineColor = '#000000';
this.graphLineAlpha = 0;
this.graphFillColor = '#000000';
this.graphFillAlpha = 0.1;
this.selectedGraphLineColor = '#000000';
this.selectedGraphLineAlpha = 0;
this.selectedGraphFillColor = '#000000';
this.selectedGraphFillAlpha = 0.5;
this.gridCount = 0;
this.gridColor = '#FFFFFF';
this.gridAlpha = 0.7;
this.scrollbarCreated = this.skipEvent = this.autoGridCount =
!1
},
init: function () {
var a = this.categoryAxis,
b = this.chart;
a || (this.categoryAxis = a = new AmCharts.CategoryAxis);
a.chart = b;
a.id = 'scrollbar';
a.dateFormats = b.categoryAxis.dateFormats;
a.axisItemRenderer = AmCharts.RectangularAxisItemRenderer;
a.axisRenderer = AmCharts.RectangularAxisRenderer;
a.guideFillRenderer = AmCharts.RectangularAxisGuideFillRenderer;
a.inside = !0;
a.fontSize = this.fontSize;
a.tickLength = 0;
a.axisAlpha = 0;
this.graph && (a = this.valueAxis, a || (this.valueAxis = a = new AmCharts.ValueAxis, a.visible = !1, a.scrollbar =
!0, a.axisItemRenderer = AmCharts.RectangularAxisItemRenderer, a.axisRenderer = AmCharts.RectangularAxisRenderer, a.guideFillRenderer = AmCharts.RectangularAxisGuideFillRenderer, a.chart = b), b = this.selectedGraph, b || (b = new AmCharts.AmGraph, b.scrollbar = !0, this.selectedGraph = b), b = this.unselectedGraph, b || (b = new AmCharts.AmGraph, b.scrollbar = !0, this.unselectedGraph = b));
this.scrollbarCreated = !0
},
draw: function () {
var a = this;
AmCharts.ChartScrollbar.base.draw.call(a);
a.scrollbarCreated || a.init();
var b = a.chart,
c = b.chartData,
d = a.categoryAxis,
e = a.rotate,
f = a.x,
g = a.y,
h = a.width,
j = a.height,
l = b.categoryAxis;
d.setOrientation(!e);
d.parseDates = l.parseDates;
d.rotate = e;
d.equalSpacing = l.equalSpacing;
d.minPeriod = l.minPeriod;
d.startOnAxis = l.startOnAxis;
d.x = f;
d.y = g;
d.visibleAxisWidth = h;
d.visibleAxisHeight = j;
d.visibleAxisX = f;
d.visibleAxisY = g;
d.width = h;
d.height = j;
d.gridCount = a.gridCount;
d.gridColor = a.gridColor;
d.gridAlpha = a.gridAlpha;
d.color = a.color;
d.autoGridCount = a.autoGridCount;
d.parseDates && !d.equalSpacing && d.timeZoom(c[0].time, c[c.length -
1].time);
d.zoom(0, c.length - 1);
if (l = a.graph) {
var k = a.valueAxis,
n = l.valueAxis;
k.id = n.id;
k.rotate = e;
k.setOrientation(e);
k.x = f;
k.y = g;
k.width = h;
k.height = j;
k.visibleAxisX = f;
k.visibleAxisY = g;
k.visibleAxisWidth = h;
k.visibleAxisHeight = j;
k.dataProvider = c;
k.reversed = n.reversed;
k.logarithmic = n.logarithmic;
for (var o = Infinity, m = - Infinity, p = 0; p < c.length; p++) {
  var s = c[p].axes[n.id].graphs[l.id].values,
  q;
  for (q in s) if ('percents' != q && 'total' != q) {
    var r = s[q];
    r < o && (o = r);
    r > m && (m = r)
  }
}
Infinity != o && (k.minimum = o);
- Infinity !=
m && (k.maximum = m + 0.1 * (m - o));
o == m && (k.minimum -= 1, k.maximum += 1);
k.zoom(0, c.length - 1);
q = a.unselectedGraph;
q.id = l.id;
q.rotate = e;
q.chart = b;
q.chartType = b.chartType;
q.data = c;
q.valueAxis = k;
q.chart = l.chart;
q.categoryAxis = a.categoryAxis;
q.valueField = l.valueField;
q.openField = l.openField;
q.closeField = l.closeField;
q.highField = l.highField;
q.lowField = l.lowField;
q.lineAlpha = a.graphLineAlpha;
q.lineColor = a.graphLineColor;
q.fillAlphas = [
  a.graphFillAlpha
];
q.fillColors = [
  a.graphFillColor
];
q.connect = l.connect;
q.hidden = l.hidden;
q.width = h;
q.height = j;
q.x = f;
q.y = g;
n = a.selectedGraph;
n.id = l.id;
n.rotate = e;
n.chart = b;
n.chartType = b.chartType;
n.data = c;
n.valueAxis = k;
n.chart = l.chart;
n.categoryAxis = d;
n.valueField = l.valueField;
n.openField = l.openField;
n.closeField = l.closeField;
n.highField = l.highField;
n.lowField = l.lowField;
n.lineAlpha = a.selectedGraphLineAlpha;
n.lineColor = a.selectedGraphLineColor;
n.fillAlphas = [
  a.selectedGraphFillAlpha
];
n.fillColors = [
  a.selectedGraphFillColor
];
n.connect = l.connect;
n.hidden = l.hidden;
n.width = h;
n.height = j;
n.x =
f;
n.y = g;
b = a.graphType;
b || (b = l.type);
q.type = b;
n.type = b;
c = c.length - 1;
q.zoom(0, c);
n.zoom(0, c);
c = a.dragger;
n.set.insertBefore(c);
q.set.insertBefore(c);
n.set.click(function () {
  a.handleBackgroundClick()
}).mouseover(function () {
  a.handleMouseOver()
}).mouseout(function () {
  a.handleMouseOut()
});
q.set.click(function () {
  a.handleBackgroundClick()
}).mouseover(function () {
  a.handleMouseOver()
}).mouseout(function () {
  a.handleMouseOut()
})
}
a.dragger.toFront();
a.invisibleBg.insertBefore(a.dragger);
a.dragIconLeft.toFront();
a.dragIconRight.toFront()
},
timeZoom: function (a, b) {
this.startTime = a;
this.endTime = b;
this.timeDifference = b - a;
this.skipEvent = !0;
this.zoomScrollbar()
},
zoom: function (a, b) {
this.start = a;
this.end = b;
this.skipEvent = !0;
this.zoomScrollbar()
},
dispatchScrollbarEvent: function () {
if (this.skipEvent) this.skipEvent = !1;
 else {
var a = this.chart.chartData,
b,
c,
d = this.dragger.getBBox();
b = d.x;
var e = d.y;
c = d.width;
d = d.height;
this.rotate && (b = e, c = d);
e = this.categoryAxis;
d = this.stepWidth;
if (e.parseDates && !e.equalSpacing) {
  a = a[0].time;
  b = this.rotate ? b - this.y : b -
  this.x;
  var f = e.minDuration(),
  e = Math.round(b / d) + a,
  a = this.dragging ? e + this.timeDifference : Math.round((b + c) / d) + a - f;
  e > a && (e = a);
  if (e != this.startTime || a != this.endTime) this.startTime = e,
  this.endTime = a,
  b = {
    type: 'zoomed',
    start: e,
    end: a,
    startDate: new Date(e),
    endDate: new Date(a)
  },
  this.fire(b.type, b)
} else if (e.startOnAxis || (b += d / 2), c -= this.stepWidth / 2, d = e.xToIndex(b), b = e.xToIndex(b + c), d != this.start || this.end != b) e.startOnAxis && (this.resizingRight && d == b && b++, this.resizingLeft && d == b && (0 < d ? d-- : b = 1)),
this.start = d,
this.end =
this.dragging ? this.start + this.difference : b,
b = {
  type: 'zoomed',
  start: this.start,
  end: this.end
},
e.parseDates && (a[this.start] && (b.startDate = new Date(a[this.start].time)), a[this.end] && (b.endDate = new Date(a[this.end].time))),
this.fire(b.type, b)
}
},
zoomScrollbar: function () {
var a,
b;
b = this.chart.chartData;
var c = this.categoryAxis,
d;
c.parseDates && !c.equalSpacing ? (d = c.stepWidth, b = b[0].time, a = d * (this.startTime - b), b = d * (this.endTime - b + c.minDuration()), this.rotate ? (a += this.y, b += this.y)  : (a += this.x, b += this.x))  : (a = b[this.start].x[c.id], b = b[this.end].x[c.id], d = c.stepWidth, c.startOnAxis || (c = d / 2, a -= c, b += c));
this.stepWidth = d;
this.updateScrollbarSize(a, b)
},
maskGraphs: function (a) {
var b = this.selectedGraph;
if (b) for (var b = b.set, c = 0; c < b.length; c++) b[c].attr({
'clip-rect': a
})
},
handleDragStart: function () {
AmCharts.ChartScrollbar.base.handleDragStart.call(this);
this.difference = this.end - this.start;
this.timeDifference = this.endTime - this.startTime;
0 > this.timeDifference && (this.timeDifference = 0)
},
handleBackgroundClick: function () {
AmCharts.ChartScrollbar.base.handleBackgroundClick.call(this);
this.dragging || (this.difference = this.end - this.start, this.timeDifference = this.endTime - this.startTime, 0 > this.timeDifference && (this.timeDifference = 0))
}
});
AmCharts.circle = function (a, b, c, d, e, f, g, h) {
if (void 0 == e || 0 == e) e = 1;
void 0 == f && (f = '#000000');
void 0 == g && (g = 0);
h && (c = 'r' + c + '-' + AmCharts.adjustLuminosity(c, - 0.6));
c = {
fill: c,
stroke: f,
'fill-opacity': d,
'stroke-width': e,
'stroke-opacity': g
};
return a.circle(0, 0, b).attr(c)
};
AmCharts.text = function (a, b, c, d, e) {
a = a.text(b, c, d).attr(e);
window.opera && a.translate('0,-2');
return a
};
AmCharts.polygon = function (a, b, c, d, e, f, g, h, j) {
'object' == typeof e && (e = e[0]);
if (void 0 == f || 0 == f) f = 1;
void 0 == g && (g = '#000000');
void 0 == h && (h = 0);
void 0 == j && (j = 270);
d = {
fill: '' + AmCharts.generateGradient(d, j),
stroke: g,
'fill-opacity': e,
'stroke-width': f,
'stroke-opacity': h
};
e = AmCharts.ddd;
f = [
'M',
Math.round(b[0]) + e,
Math.round(c[0]) + e
];
for (g = 1; g < b.length; g++) f.push('L'),
f.push(Math.round(b[g]) + e),
f.push(Math.round(c[g]) + e);
f.push('Z');
return a.path(f).attr(d)
};
AmCharts.rect = function (a, b, c, d, e, f, g, h, j, l) {
if (void 0 == f || 0 == f) f = 1;
void 0 == g && (g = '#000000');
void 0 == h && (h = 0);
void 0 == j && (j = 0);
void 0 == l && (l = 270);
'object' == typeof e && (e = e[0]);
void 0 == e && (e = 0);
var b = Math.round(b),
c = Math.round(c),
k = 0,
n = 0;
0 > b && (b = Math.abs(b), k = - b);
0 > c && (c = Math.abs(c), n = - c);
k += AmCharts.ddd;
n += AmCharts.ddd;
(d = AmCharts.generateGradient(d, l)) || (d = '#FFFFFF');
e = {
fill: '' + d,
stroke: g,
'fill-opacity': e,
'stroke-width': f,
'stroke-opacity': h
};
return a.rect(k, n, b, c, j).attr(e)
};
AmCharts.triangle = function (a, b, c, d, e, f, g, h) {
if (void 0 == f || 0 == f) f = 1;
void 0 == g && (g = '#000000');
void 0 == h && (h = 0);
d = {
fill: d,
stroke: g,
'fill-opacity': e,
'stroke-width': f,
'stroke-opacity': h
};
a = a.path(['M',
- b / 2,
b / 2,
'L',
0,
- b / 2,
'L',
b / 2,
b / 2,
'Z',
- b / 2,
b / 2]).attr(d);
a.transform('r' + c);
return a
};
AmCharts.line = function (a, b, c, d, e, f, g) {
var h = '';
1 == g && (h = '. ');
1 < g && (h = '- ');
d = {
stroke: d,
'stroke-dasharray': h,
'stroke-opacity': e,
'stroke-width': f
};
e = AmCharts.ddd;
f = [
'M',
Math.round(b[0]) + e,
Math.round(c[0]) + e
];
for (g = 1; g < b.length; g++) f.push('L'),
f.push(Math.round(b[g]) + e),
f.push(Math.round(c[g]) + e);
return a.path(f).attr(d)
};
AmCharts.wedge = function (a, b, c, d, e, f, g, h, j, l) {
var k = g / f * h;
- 359.99 >= e && (e = - 359.99);
var n = b + Math.cos(d / 180 * Math.PI) * h,
o = c + Math.sin( - d / 180 * Math.PI) * k,
m = b + Math.cos(d / 180 * Math.PI) * f,
p = c + Math.sin( - d / 180 * Math.PI) * g,
s = b + Math.cos((d + e) / 180 * Math.PI) * f,
q = c + Math.sin(( - d - e) / 180 * Math.PI) * g,
b = b + Math.cos((d + e) / 180 * Math.PI) * h,
c = c + Math.sin(( - d - e) / 180 * Math.PI) * k,
d = AmCharts.adjustLuminosity(l.fill, - 0.2),
r = {
fill: d,
'fill-opacity': l['fill-opacity'],
stroke: d,
'stroke-width': 0.000001,
'stroke-opacity': 0.00001
},
d = 0;
180 < Math.abs(e) && (d = 1);
e = a.set();
if (0 < j) {
var v = 0 < h ? a.path(['M',
n,
o + j,
'L',
m,
p + j,
'A',
f,
g,
0,
d,
1,
s,
q + j,
'L',
b,
c + j,
'A',
h,
k,
0,
d,
0,
n,
o + j,
'z']).attr(r)  : a.path(['M',
n,
o + j,
'L',
m,
p + j,
'A',
f,
g,
0,
d,
1,
s,
q + j,
'L',
b,
c + j,
'Z']).attr(r);
e.push(v);
v = a.path(['M',
n,
o,
'L',
n,
o + j,
'L',
m,
p + j,
'L',
m,
p,
'L',
n,
o,
'z']).attr(r);
j = a.path(['M',
s,
q,
'L',
s,
q + j,
'L',
b,
c + j,
'L',
b,
c,
'L',
s,
q,
'z']).attr(r);
e.push(v);
e.push(j)
}
l.gradient && (l.fill = null);
a = 0 < h ? a.path(['M',
n,
o,
'L',
m,
p,
'A',
f,
g,
0,
d,
1,
s,
q,
'L',
b,
c,
'A',
h,
k,
0,
d,
0,
n,
o,
'Z']).attr(l)  : a.path(['M',
n,
o,
'L',
m,
p,
'A',
f,
g,
0,
d,
1,
s,
q,
'L',
b,
c,
'Z']).attr(l);
e.push(a);
return e
};
AmCharts.adjustLuminosity = function (a, b) {
var c = Raphael.rgb2hsb(a).toString().split(','),
d = c[2],
d = Number(d.substr(0, d.length - 1)),
d = d + d * b;
1 < d && (d = 1);
return c[0] + ',' + c[1] + ',' + d + ')'
};
AmCharts.putSetToFront = function (a) {
for (var b = a.length - 1; 0 >= b; b++) a[b].toFront()
};
AmCharts.putSetToBack = function (a) {
for (var b = 0; b < a.length - 1; b++) a[b].toBack()
};
AmCharts.AmPieChart = AmCharts.Class({
inherits: AmCharts.AmChart,
construct: function () {
this.createEvents('rollOverSlice', 'rollOutSlice', 'clickSlice', 'pullOutSlice', 'pullInSlice');
AmCharts.AmPieChart.base.construct.call(this);
this.colors = '#FF0F00,#FF6600,#FF9E01,#FCD202,#F8FF01,#B0DE09,#04D215,#0D8ECF,#0D52D1,#2A0CD0,#8A0CCF,#CD0D74,#754DEB,#DDDDDD,#999999,#333333,#000000,#57032A,#CA9726,#990000,#4B0C25'.split(',');
this.pieAlpha = 1;
this.pieBaseColor;
this.pieBrightnessStep = 30;
this.groupPercent = 0;
this.groupedTitle =
'Other';
this.groupedPulled = !1;
this.groupedAlpha = 1;
this.marginLeft = 0;
this.marginBottom = this.marginTop = 10;
this.marginRight = 0;
this.minRadius = 10;
this.hoverAlpha = 1;
this.depth3D = 0;
this.startAngle = 90;
this.angle = this.innerRadius = 0;
this.outlineColor = '#FFFFFF';
this.outlineAlpha = 0;
this.outlineThickness = 1;
this.startRadius = '500%';
this.startAlpha = 0;
this.startDuration = 1;
this.startEffect = 'bounce';
this.sequencedAnimation = !1;
this.pullOutRadius = '20%';
this.pullOutDuration = 1;
this.pullOutEffect = 'bounce';
this.pullOnHover =
this.pullOutOnlyOne = !1;
this.labelsEnabled = !0;
this.labelRadius = 30;
this.labelTickColor = '#000000';
this.labelTickAlpha = 0.2;
this.labelText = '[[title]]: [[percents]]%';
this.hideLabelsPercent = 0;
this.balloonText = '[[title]]: [[percents]]% ([[value]])\n[[description]]';
this.dataProvider;
this.urlTarget = '_self';
this.previousScale = 1;
this.autoMarginOffset = 10
},
initChart: function () {
AmCharts.AmPieChart.base.initChart.call(this);
this.dataChanged && (this.parseData(), this.dispatchDataUpdated = !0, this.dataChanged = !1, this.legend &&
this.legend.setData(this.chartData));
this.drawChart()
},
handleLegendEvent: function (a) {
var b = a.type;
if (a = a.dataItem) {
var c = a.hidden;
switch (b) {
  case 'clickMarker':
    c || this.clickSlice(a);
    break;
  case 'clickLabel':
    c || this.clickSlice(a);
    break;
  case 'rollOverItem':
    c || this.rollOverSlice(a, !1);
    break;
  case 'rollOutItem':
    c || this.rollOutSlice(a);
    break;
  case 'hideItem':
    this.hideSlice(a);
    break;
  case 'showItem':
    this.showSlice(a)
}
}
},
invalidateVisibility: function () {
this.recalculatePercents();
this.drawChart();
var a = this.legend;
a && a.invalidateSize()
},
drawChart: function () {
var a = this;
AmCharts.AmPieChart.base.drawChart.call(a);
var b = a.chartData;
if (b && 0 < b.length) {
var c = a.updateWidth();
a.realWidth = c;
var d = a.updateHeight();
a.realHeight = d;
var e = AmCharts.toCoordinate,
f = e(a.marginLeft, c),
g = e(a.marginRight, c),
h = e(a.marginTop, d) + a.getTitleHeight(),
j = e(a.marginBottom, d);
a.chartDataLabels = [
];
a.ticks = [
];
var l,
k,
n,
o = AmCharts.toNumber(a.labelRadius),
m = a.measureMaxLabel();
if (!a.labelText || !a.labelsEnabled) o = m = 0;
l = void 0 == a.pieX ? (c - f - g) /
2 + f : e(a.pieX, a.realWidth);
k = void 0 == a.pieY ? (d - h - j) / 2 + h : e(a.pieY, d);
n = e(a.radius, c, d);
a.pullOutRadiusReal = AmCharts.toCoordinate(a.pullOutRadius, n);
n || (c = 0 <= o ? c - f - g - 2 * m : c - f - g, d = d - h - j, n = Math.min(c, d), d < c && (n /= 1 - a.angle / 90, n > c && (n = c)), a.pullOutRadiusReal = AmCharts.toCoordinate(a.pullOutRadius, n), n = 0 <= o ? n - 1.8 * (o + a.pullOutRadiusReal)  : n - 1.8 * a.pullOutRadiusReal, n /= 2);
n < a.minRadius && (n = a.minRadius);
a.pullOutRadiusReal = e(a.pullOutRadius, n);
e = e(a.innerRadius, n);
e >= n && (e = n - 1);
d = AmCharts.fitToBounds(a.startAngle, 0, 360);
0 < a.depth3D && (d = 270 <= d ? 270 : 90);
h = n - n * a.angle / 90;
for (j = 0; j < b.length; j++) if (c = b[j], !0 != c.hidden && 0 < c.percents) {
  var p = 360 * - c.percents / 100,
  m = Math.cos((d + p / 2) / 180 * Math.PI),
  g = Math.sin(( - d - p / 2) / 180 * Math.PI) * (h / n),
  f = {
    fill: c.color,
    'fill-opacity': a.startAlpha,
    stroke: a.outlineColor,
    'stroke-opacity': a.outlineAlpha,
    'stroke-width': a.outlineThickness,
    'stroke-linecap': 'round',
    cursor: c.url ? 'pointer' : ''
  },
  s = l,
  q = k;
  a.chartCreated && (f['fill-opacity'] = c.alpha);
  f = AmCharts.wedge(a.container, s, q, d, p, n, h, e, a.depth3D, f);
  b[j].wedge = f;
  90 >= d && 0 <= d || 360 >= d && 270 < d ? AmCharts.putSetToFront(f)  : (270 >= d && 180 < d || 180 >= d && 90 < d) && AmCharts.putSetToBack(f);
  c.ix = m;
  c.iy = g;
  c.wedge = f;
  c.index = j;
  if (a.labelsEnabled && a.labelText && c.percents >= a.hideLabelsPercent) {
    s = d + p / 2;
    0 >= s && (s += 360);
    var m = l + m * (n + o),
    g = k + g * (n + o),
    r,
    p = 0;
    if (0 <= o) {
      var v;
      90 >= s && 0 <= s ? (v = 0, r = 'start', p = 8)  : 360 >= s && 270 < s ? (v = 1, r = 'start', p = 8)  : 270 >= s && 180 < s ? (v = 2, r = 'end', p = - 8)  : 180 >= s && 90 < s && (v = 3, r = 'end', p = - 8);
      c.labelQuarter = v
    } else r = 'middle';
    s = a.formatString(a.labelText, c);
    s = AmCharts.text(a.container, m + 1.5 * p, g, s, {
      fill: a.color,
      'text-anchor': r,
      'font-family': a.fontFamily,
      'font-size': a.fontSize
    });
    q = setTimeout(function () {
      a.showLabels.call(a)
    }, 1000 * a.startDuration);
    a.timeOuts.push(q);
    a.touchEventsEnabled && (f.touchend(function () {
      handleTouchEnd(a.chartData[this.index])
    }), f.touchstart(function () {
      handleTouchStart(a.chartData[this.index])
    }));
    f.push(s);
    c.labelObject = s;
    a.chartDataLabels[j] = s;
    s.cornerx = m;
    s.cornery = g;
    s.cornerx2 = m + p
  }
  for (g = 0; g < f.length; g++) f[g].index = j;
  f.hover(function () {
    a.rollOverSlice(a.chartData[this.index], !0)
  }, function () {
    a.rollOutSlice(a.chartData[this.index])
  }).click(function () {
    a.clickSlice(a.chartData[this.index])
  });
  a.set.push(f);
  0 == c.alpha && f.hide();
  d -= 360 * c.percents / 100;
  0 >= d && (d += 360)
}
0 < o && a.arrangeLabels();
for (j = 0; j < a.chartDataLabels.length; j++) a.chartDataLabels[j] && a.chartDataLabels[j].toFront();
a.pieXReal = l;
a.pieYReal = k;
a.radiusReal = n;
a.innerRadiusReal = e;
0 < o && a.drawTicks();
a = this;
a.chartCreated ? a.pullSlices(!0)  : (q = setTimeout(function () {
  a.pullSlices.call(a)
}, 1200 * a.startDuration), a.timeOuts.push(q));
a.chartCreated || a.startSlices();
a.bringLabelsToFront();
a.chartCreated = !0;
a.dispatchDataUpdatedEvent()
}
a.bgImg && a.bgImg.toBack();
a.background && a.background.toBack();
a.drb()
},
formatString: function (a, b) {
a = AmCharts.formatValue(a, b, [
'value'
], this.numberFormatter, '', this.usePrefixes, this.prefixesOfSmallNumbers, this.prefixesOfBigNumbers);
a = AmCharts.formatValue(a, b, [
'percents'
], this.percentFormatter);
a = AmCharts.massReplace(a, {
'[[title]]': b.title,
'[[description]]': b.description,
'<br>': '\n'
});
return a = AmCharts.cleanFromEmpty(a)
},
drawTicks: function () {
for (var a = 0; a < this.chartData.length; a++) if (this.chartDataLabels[a]) {
var b = this.chartData[a],
c = this.chartDataLabels[a],
d = c.cornery,
c = this.container.path(['M',
this.pieXReal + b.ix * this.radiusReal,
this.pieYReal + b.iy * this.radiusReal,
'L',
c.cornerx,
d,
'L',
c.cornerx2,
d]).attr({
  stroke: this.labelTickColor,
  'stroke-opacity': this.labelTickAlpha,
  'stroke-width': 1,
  'stroke-linecap': 'round'
});
b.wedge.push(c);
this.chartCreated || b.wedge.hide();
this.ticks[a] = c
}
},
arrangeLabels: function () {
for (var a = this.chartData, b = a.length, c = this.chartDataLabels, d, e = b - 1; 0 <= e; e--) d = a[e],
0 == d.labelQuarter && !d.hidden && c[e] && (d = d.index, this.checkOverlapping(d, 0, !0, 0));
for (e = 0; e < b; e++) d = a[e],
1 == d.labelQuarter && !d.hidden && c[e] && (d = d.index, this.checkOverlapping(d, 1, !1, 0));
for (e = b - 1; 0 <= e; e--) d = a[e],
2 == d.labelQuarter && !d.hidden && c[e] && (d = d.index, this.checkOverlapping(d, 2, !0, 0));
for (e = 0; e < b; e++) d = a[e],
3 == d.labelQuarter && !d.hidden && c[e] && (d = d.index, this.checkOverlapping(d, 3, !1, 0))
},
checkOverlapping: function (a, b, c, d) {
var e,
f,
g,
h = this.chartData,
j = this.chartDataLabels;
if (!0 == c) for (f = a + 1; f < h.length; f++) g = h[f],
g.labelQuarter == b && !g.hidden && j[f] && !0 == AmCharts.hitTest(j[a].getBBox(), j[f].getBBox()) && (e = !0);
 else for (f = a - 1; 0 <= f; f--) g = h[f],
g.labelQuarter == b && !g.hidden && j[f] && !0 == AmCharts.hitTest(j[a].getBBox(), j[f].getBBox()) && (e = !0);
g = j[a].getBBox();
j[a].cornery = g.y += g.height / 2;
!0 == e && 100 > d && (g = h[a], e = j[a], e.attr({
y: e.attr('y') + 3 * g.iy
}), this.checkOverlapping(a, b, c, d + 1))
},
startSlices: function () {
for (var a = this, b = 500 * (a.startDuration / a.chartData.length), c = 0; c < a.chartData.length; c++) if (0 < a.startDuration && a.sequencedAnimation) {
var d = setTimeout(function () {
  a.startSequenced.call(a)
}, b * c);
a.timeOuts.push(d)
} else a.startSlice(a.chartData[c])
},
pullSlices: function (a) {
for (var b = this.chartData, c = 0; c < b.length; c++) b[c].pulled && this.pullSlice(b[c], 1, a)
},
startSequenced: function () {
for (var a = this.chartData, b = 0; b < a.length; b++) if (!a[b].started) {
this.startSlice(this.chartData[b]);
break
}
},
startSlice: function (a) {
a.started = !0;
var b = a.wedge;
if (b) {
0 < a.alpha && b.show();
var c =
AmCharts.toCoordinate(this.startRadius, this.radiusReal);
b.translate(a.ix * c + ',' + a.iy * c);
b.animate({
  'fill-opacity': a.alpha,
  transform: 't0,0'
}, 1000 * this.startDuration, this.startEffect)
}
},
showLabels: function () {
for (var a = this.chartData, b = 0; b < a.length; b++) if (0 < a[b].alpha) {
var c = this.chartDataLabels[b];
c && c.show();
(c = this.ticks[b]) && c.show()
}
},
showSlice: function (a) {
isNaN(a) ? a.hidden = !1 : this.chartData[a].hidden = !1;
this.hideBalloon();
this.invalidateVisibility()
},
hideSlice: function (a) {
isNaN(a) ? a.hidden = !0 : this.chartData[a].hidden =
!0;
this.hideBalloon();
this.invalidateVisibility()
},
rollOverSlice: function (a, b) {
isNaN(a) || (a = this.chartData[a]);
clearTimeout(this.hoverInt);
this.pullOnHover && this.pullSlice(a, 1);
var c = this.innerRadiusReal + (this.radiusReal - this.innerRadiusReal) / 2;
a.pulled && (c += this.pullOutRadiusReal);
1 > this.hoverAlpha && a.wedge && a.wedge.attr({
'fill-opacity': this.hoverAlpha
});
var d = a.ix * c + this.pieXReal,
c = a.iy * c + this.pieYReal,
e = this.formatString(this.balloonText, a),
f = AmCharts.adjustLuminosity(a.color, - 0.15);
this.showBalloon(e, f, b, d, c);
d = {
type: 'rollOverSlice',
dataItem: a
};
this.fire(d.type, d)
},
rollOutSlice: function (a) {
isNaN(a) || (a = this.chartData[a]);
a.wedge && a.wedge.attr({
'fill-opacity': a.alpha
});
this.hideBalloon();
a = {
type: 'rollOutSlice',
dataItem: a
};
this.fire(a.type, a)
},
clickSlice: function (a) {
isNaN(a) || (a = this.chartData[a]);
this.hideBalloon();
a.pulled ? this.pullSlice(a, 0)  : this.pullSlice(a, 1);
var b = a.url,
c = this.urlTarget;
b && ('_self' == c || !c ? window.location.href = b : (c = document.getElementsByName(c) [0]) ? c.src = b : window.open(b));
a = {
type: 'clickSlice',
dataItem: a
};
this.fire(a.type, a)
},
pullSlice: function (a, b, c) {
var d = a.ix,
e = a.iy,
f = 1000 * this.pullOutDuration;
!0 === c && (f = 0);
a.wedge && a.wedge.animate({
transform: 't' + b * d * this.pullOutRadiusReal + ',' + b * e * this.pullOutRadiusReal
}, f, this.pullOutEffect);
1 == b ? (a.pulled = !0, this.pullOutOnlyOne && this.pullInAll(a.index), a = {
type: 'pullOutSlice',
dataItem: a
})  : (a.pulled = !1, a = {
type: 'pullInSlice',
dataItem: a
});
this.fire(a.type, a)
},
pullInAll: function (a) {
for (var b = this.chartData, c = 0; c < this.chartData.length; c++) c !=
a && b[c].pulled && this.pullSlice(b[c], 0)
},
pullOutAll: function () {
for (var a = this.chartData, b = 0; b < a.length; b++) a[b].pulled || this.pullSlice(a[b], 1)
},
parseData: function () {
var a = [
];
this.chartData = a;
var b = this.dataProvider;
if (void 0 != b) {
for (var c = b.length, d = 0, e = 0; e < c; e++) {
  var f = {
  },
  g = b[e];
  f.dataContext = g;
  f.value = Number(g[this.valueField]);
  var h = g[this.titleField];
  h || (h = '');
  f.title = h;
  f.pulled = AmCharts.toBoolean(g[this.pulledField], !1);
  (h = g[this.descriptionField]) || (h = '');
  f.description = h;
  f.url = g[this.urlField];
  f.visibleInLegend = AmCharts.toBoolean(g[this.pulledField], !0);
  h = g[this.alphaField];
  f.alpha = void 0 != h ? Number(h)  : this.pieAlpha;
  g = g[this.colorField];
  void 0 != g && (f.color = AmCharts.toColor(g));
  d += f.value;
  f.hidden = !1;
  a[e] = f
}
for (e = b = 0; e < c; e++) f = a[e],
f.percents = 100 * (f.value / d),
f.percents < this.groupPercent && b++;
1 < b && (this.groupValue = 0, this.removeSmallSlices(), a.push({
  title: this.groupedTitle,
  value: this.groupValue,
  percents: 100 * (this.groupValue / d),
  pulled: this.groupedPulled,
  color: this.groupedColor,
  url: this.groupedUrl,
  description: this.groupedDescription,
  alpha: this.groupedAlpha
}));
for (e = 0; e < a.length; e++) this.pieBaseColor ? g = AmCharts.adjustLuminosity(this.pieBaseColor, e * this.pieBrightnessStep / 100)  : (g = this.colors[e], void 0 == g && (g = AmCharts.randomColor())),
void 0 == a[e].color && (a[e].color = g);
this.recalculatePercents()
}
},
recalculatePercents: function () {
for (var a = this.chartData, b = 0, c = 0; c < a.length; c++) {
var d = a[c];
!d.hidden && 0 < d.value && (b += d.value)
}
for (c = 0; c < a.length; c++) d = this.chartData[c],
d.percents = !d.hidden && 0 < d.value ?
100 * d.value / b : 0
},
handleTouchStart: function (a) {
var b = this;
AmCharts.AmPieChart.base.handleTouchStart.call(b);
a.pulled ? (b.rolledOveredSlice = void 0, b.hideBalloon())  : (b.rolledOveredSlice = a, b.pullTimeOut = setTimeout(function () {
b.padRollOver.call(b)
}, 100))
},
padRollOver: function () {
this.rollOverSlice(this.rolledOveredSlice, !1)
},
handleTouchEnd: function (a) {
AmCharts.AmPieChart.base.handleTouchEnd.call(this);
a.pulled ? this.pullSlice(a, 0)  : this.pullSlice(a, 1)
},
removeSmallSlices: function () {
for (var a = this.chartData, b = a.length - 1; 0 <= b; b--) a[b].percents < this.groupPercent && (this.groupValue += a[b].value, a.splice(b, 1))
},
animateAgain: function () {
var a = this;
a.startSlices();
var b = setTimeout(function () {
a.pullSlices.call(a)
}, 1200 * a.startDuration);
a.timeOuts.push(b)
},
measureMaxLabel: function () {
for (var a = this.chartData, b = 0, c = 0; c < a.length; c++) {
var d = this.formatString(this.labelText, a[c]),
d = AmCharts.text(this.container, 0, 0, d, {
  fill: this.color,
  'font-family': this.fontFamily,
  'font-size': this.fontSize
}),
e = d.getBBox().width;
e > b && (b = e);
d.remove()
}
return b
}
}); AmCharts.AmXYChart = AmCharts.Class({
inherits: AmCharts.AmRectangularChart,
construct: function () {
AmCharts.AmXYChart.base.construct.call(this);
this.createEvents('zoomed');
this.xAxes;
this.yAxes;
this.scrollbarVertical;
this.scrollbarHorizontal;
this.maxZoomFactor = 20;
this.chartType = 'xy'
},
initChart: function () {
AmCharts.AmXYChart.base.initChart.call(this);
this.dataChanged && (this.updateData(), this.dataChanged = !1, this.dispatchDataUpdated = !0);
this.updateScrollbar = !0;
this.drawChart();
this.autoMargins && !this.marginsUpdated && (this.marginsUpdated = !0, this.measureMargins())
},
createValueAxes: function () {
var a = [
],
b = [
];
this.xAxes = a;
this.yAxes = b;
for (var c = this.valueAxes, d = 0; d < c.length; d++) {
var e = c[d],
f = e.position;
if ('top' == f || 'bottom' == f) e.rotate = !0;
e.setOrientation(e.rotate);
f = e.orientation;
'vertical' == f && b.push(e);
'horizontal' == f && a.push(e)
}
0 == b.length && (e = new AmCharts.ValueAxis, e.rotate = !1, e.setOrientation(!1), c.push(e), b.push(e));
0 == a.length && (e = new AmCharts.ValueAxis, e.rotate = !0, e.setOrientation(!0), c.push(e), a.push(e));
for (d = 0; d < c.length; d++) this.processValueAxis(c[d], d);
a = this.graphs;
for (d = 0; d < a.length; d++) this.processGraph(a[d], d)
},
drawChart: function () {
AmCharts.AmXYChart.base.drawChart.call(this);
var a = this.chartData;
a && (0 < a.length ? (this.chartScrollbar && (this.updateScrollbars(), this.scrollbarHorizontal.draw(), this.scrollbarVertical.draw()), this.zoomChart())  : this.cleanChart());
this.bringLabelsToFront();
this.chartCreated = !0;
this.dispatchDataUpdatedEvent()
},
cleanChart: function () {
AmCharts.callMethod('destroy', [
this.valueAxes,
this.graphs,
this.scrollbarVertical,
this.scrollbarHorizontal,
this.chartCursor
])
},
zoomChart: function () {
this.toggleZoomOutButton();
this.zoomObjects(this.valueAxes);
this.zoomObjects(this.graphs);
this.drawTrendLines();
this.dispatchAxisZoom();
this.updateDepths();
this.renderfix()
},
toggleZoomOutButton: function () {
1 == this.heightMultiplyer && 1 == this.widthMultiplyer ? this.hideZoomOutButton()  : this.showZoomOutButton()
},
dispatchAxisZoom: function () {
for (var a = this.valueAxes, b = 0; b < a.length; b++) {
var c = a[b];
if (!isNaN(c.min) &&
!isNaN(c.max)) {
  var d,
  e;
  'vertical' == c.orientation ? (d = c.coordinateToValue( - this.verticalPosition), e = c.coordinateToValue( - this.verticalPosition + this.plotAreaHeight))  : (d = c.coordinateToValue( - this.horizontalPosition), e = c.coordinateToValue( - this.horizontalPosition + this.plotAreaWidth));
  if (!isNaN(d) && !isNaN(e)) {
    if (d > e) {
      var f = e;
      e = d;
      d = f
    }
    c.dispatchZoomEvent(d, e)
  }
}
}
},
zoomObjects: function (a) {
for (var b = a.length, c = 0; c < b; c++) {
var d = a[c];
this.updateObjectSize(d);
d.zoom(0, this.chartData.length - 1)
}
},
drawTrendLines: function () {
var a =
this.trendLines;
for (i = 0; i < a.length; i++) a[i].draw()
},
updateData: function () {
this.parseData();
for (var a = this.chartData, b = a.length - 1, c = this.graphs, d = this.dataProvider, e = 0; e < c.length; e++) {
var f = c[e];
f.data = a;
f.zoom(0, b);
var g = f.valueField,
h = 0;
if (g) for (var j = 0; j < d.length; j++) {
  var l = d[j][g];
  l > h && (h = l)
}
f.maxValue = h
}
if (a = this.chartCursor) a.updateData(),
a.type = 'crosshair',
a.valueBalloonsEnabled = !1
},
zoomOut: function () {
this.verticalPosition = this.horizontalPosition = 0;
this.heightMultiplyer = this.widthMultiplyer =
1;
this.zoomChart();
this.zoomScrollbars()
},
updateTrendLinesDepth: function () {
var a = this.trendLines;
for (i = 0; i < a.length; i++) {
var b = a[i],
c = b.set;
c && c.insertBefore(this.mostFrontObj);
if (c = b.line) b = b.valueAxis,
c.attr({
  'clip-rect': b.visibleAxisX + ',' + b.visibleAxisY + ',' + b.visibleAxisWidth + ',' + b.visibleAxisHeight
})
}
},
updateDepths: function () {
var a = this.container.rect(0, 0, 10, 10);
this.mostFrontObj = a;
var b = this.chartCursor;
b && b.set.insertBefore(a);
for (var b = this.graphs, c = 0; c < b.length; c++) {
var d = b[c];
if (d.allBullets) for (var e =
0; e < d.allBullets.length; e++) d.allBullets[e].insertBefore(a);
if (d.positiveObjectsToClip) for (e = 0; e < d.positiveObjectsToClip.length; e++) d.setPositiveClipRect(d.positiveObjectsToClip[e]);
var f = d.objectsToAddListeners;
if (f) for (e = 0; e < f.length; e++) d.addClickListeners(f[e]),
d.addHoverListeners(f[e])
}
this.updateTrendLinesDepth();
(b = this.zoomOutButtonSet) && b.insertBefore(a);
this.lens && this.lens.insertBefore(a);
a.remove();
(a = this.bgImg) && a.toBack();
(a = this.background) && a.toBack();
this.drb()
},
processValueAxis: function (a) {
a.chart =
this;
a.minMaxField = 'horizontal' == a.orientation ? 'x' : 'y';
a.minTemp = NaN;
a.maxTemp = NaN;
this.listenTo(a, 'axisSelfZoomed', this.handleAxisSelfZoom)
},
processGraph: function (a) {
a.xAxis || (a.xAxis = this.xAxes[0]);
a.yAxis || (a.yAxis = this.yAxes[0])
},
parseData: function () {
AmCharts.AmXYChart.base.parseData.call(this);
this.chartData = [
];
for (var a = this.dataProvider, b = this.valueAxes, c = this.graphs, d = 0; d < a.length; d++) {
for (var e = {
  axes: {
  },
  x: {
  },
  y: {
  }
}, f = a[d], g = 0; g < b.length; g++) {
  var h = b[g].id;
  e.axes[h] = {
  };
  e.axes[h].graphs = {
  };
  for (var j = 0; j < c.length; j++) {
    var l = c[j],
    k = l.id;
    if (l.xAxis.id == h || l.yAxis.id == h) {
      var n = {
      };
      n.serialDataItem = e;
      n.index = d;
      var o = {
      },
      m = Number(f[l.valueField]);
      isNaN(m) || (o.value = m);
      m = Number(f[l.xField]);
      isNaN(m) || (o.x = m);
      m = Number(f[l.yField]);
      isNaN(m) || (o.y = m);
      n.values = o;
      this.processFields(l, n, f);
      n.serialDataItem = e;
      n.graph = l;
      e.axes[h].graphs[k] = n
    }
  }
}
this.chartData[d] = e
}
},
formatString: function (a, b) {
var c = b.graph.numberFormatter;
c || (c = this.numberFormatter);
a = AmCharts.formatValue(a, b.values, [
'value',
'x',
'y'
], c);
return a = AmCharts.AmSerialChart.base.formatString.call(this, a, b)
},
addChartScrollbar: function (a) {
AmCharts.callMethod('destroy', [
this.chartScrollbar,
this.scrollbarHorizontal,
this.scrollbarVertical
]);
if (a) {
var b = new AmCharts.SimpleChartScrollbar,
c = new AmCharts.SimpleChartScrollbar;
b.skipEvent = !0;
c.skipEvent = !0;
b.chart = this;
c.chart = this;
this.listenTo(b, 'zoomed', this.handleVSBZoom);
this.listenTo(c, 'zoomed', this.handleHSBZoom);
var d = 'backgroundColor,backgroundAlpha,selectedBackgroundColor,selectedBackgroundAlpha,scrollDuration,resizeEnabled,hideResizeGrips,scrollbarHeight,updateOnReleaseOnly'.split(',');
AmCharts.copyProperties(a, b, d);
AmCharts.copyProperties(a, c, d);
b.rotate = !0;
c.rotate = !1;
this.scrollbarHeight = a.scrollbarHeight;
this.scrollbarHorizontal = c;
this.scrollbarVertical = b;
this.chartScrollbar = a
}
},
updateTrendLines: function () {
for (var a = this.trendLines, b = 0; b < a.length; b++) {
var c = a[b];
c.chart = this;
c.valueAxis || (c.valueAxis = this.yAxes[0]);
c.valueAxisX || (c.valueAxisX = this.xAxes[0])
}
},
updateMargins: function () {
AmCharts.AmXYChart.base.updateMargins.call(this);
var a = this.scrollbarVertical;
a && (this.getScrollbarPosition(a, !0, this.yAxes[0].position), this.adjustMargins(a, !0));
if (a = this.scrollbarHorizontal) this.getScrollbarPosition(a, !1, this.xAxes[0].position),
this.adjustMargins(a, !1)
},
updateScrollbars: function () {
this.updateChartScrollbar(this.scrollbarVertical, !0);
this.updateChartScrollbar(this.scrollbarHorizontal, !1)
},
zoomScrollbars: function () {
var a = this.scrollbarHorizontal;
a && a.relativeZoom(this.widthMultiplyer, - this.horizontalPosition / this.widthMultiplyer);
(a = this.scrollbarVertical) && a.relativeZoom(this.heightMultiplyer, - this.verticalPosition / this.heightMultiplyer)
},
fitMultiplyer: function (a) {
a > this.maxZoomFactor && (a = this.maxZoomFactor);
return a
},
handleHSBZoom: function (a) {
var b = this.fitMultiplyer(a.multiplyer),
a = - a.position * b,
c = - (this.plotAreaWidth * b - this.plotAreaWidth);
a < c && (a = c);
this.widthMultiplyer = b;
this.horizontalPosition = a;
this.zoomChart()
},
handleVSBZoom: function (a) {
var b = this.fitMultiplyer(a.multiplyer),
a = - a.position * b,
c = - (this.plotAreaHeight * b - this.plotAreaHeight);
a < c && (a = c);
this.heightMultiplyer = b;
this.verticalPosition =
a;
this.zoomChart()
},
handleCursorZoom: function (a) {
var b = this.widthMultiplyer * this.plotAreaWidth / a.selectionWidth,
c = this.heightMultiplyer * this.plotAreaHeight / a.selectionHeight,
b = this.fitMultiplyer(b),
c = this.fitMultiplyer(c);
this.horizontalPosition = (this.horizontalPosition - a.selectionX) * b / this.widthMultiplyer;
this.verticalPosition = (this.verticalPosition - a.selectionY) * c / this.heightMultiplyer;
this.widthMultiplyer = b;
this.heightMultiplyer = c;
this.zoomChart();
this.zoomScrollbars()
},
handleAxisSelfZoom: function (a) {
if ('horizontal' ==
a.valueAxis.orientation) {
var b = this.fitMultiplyer(a.multiplyer),
a = - a.position / this.widthMultiplyer * b,
c = - (this.plotAreaWidth * b - this.plotAreaWidth);
a < c && (a = c);
this.horizontalPosition = a;
this.widthMultiplyer = b
} else b = this.fitMultiplyer(a.multiplyer),
a = - a.position / this.heightMultiplyer * b,
c = - (this.plotAreaHeight * b - this.plotAreaHeight),
a < c && (a = c),
this.verticalPosition = a,
this.heightMultiplyer = b;
this.zoomChart();
this.zoomScrollbars()
},
removeChartScrollbar: function () {
AmCharts.callMethod('destroy', [
this.scrollbarHorizontal,
this.scrollbarVertical
]);
this.scrollbarVertical = this.scrollbarHorizontal = null
},
handleReleaseOutside: function (a) {
AmCharts.AmXYChart.base.handleReleaseOutside.call(this, a);
AmCharts.callMethod('handleReleaseOutside', [
this.scrollbarHorizontal,
this.scrollbarVertical
])
}
});
