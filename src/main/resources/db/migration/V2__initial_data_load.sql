INSERT INTO "cryptocurrency" ("id", "name", "symbol") VALUES
(1,	'Bitcoin',	'BTC'),
(2,	'Ethereum',	'ETH'),
(3,	'Tether',	'USDT'),
(4,	'XRP',	'XRP'),
(5,	'Polkadot',	'DOT'),
(6,	'Cardano',	'ADA'),
(7,	'Chainlink',	'LINK'),
(8,	'Litecoin',	'LTC'),
(9,	'Binance Coin',	'BNB'),
(10,	'Bitcoin Cash',	'BCH'),
(11,	'Stellar',	'XLM'),
(12,	'Dogecoin',	'DOGE'),
(13,	'USD Coin',	'USDC'),
(14,	'Aave',	'AAVE'),
(15,	'Uniswap',	'UNI'),
(16,	'Wrapped Bitcoin',	'WBTC'),
(17,	'Bitcoin SV',	'BSV'),
(18,	'EOS',	'EOS'),
(19,	'Monero',	'XMR'),
(20,	'NEM',	'XEM'),
(21,	'TRON',	'TRX'),
(22,	'Synthetix',	'SNX'),
(23,	'Tezos',	'XTZ'),
(24,	'Maker',	'MKR'),
(25,	'Compound',	'COMP'),
(26,	'THETA',	'THETA'),
(27,	'SushiSwap',	'SUSHI'),
(28,	'Cosmos',	'ATOM'),
(29,	'UMA',	'UMA'),
(30,	'VeChain',	'VET'),
(31,	'Dai',	'DAI'),
(32,	'Solana',	'SOL'),
(33,	'Neo',	'NEO'),
(34,	'Huobi Token',	'HT'),
(35,	'Crypto.com Coin',	'CRO'),
(36,	'Binance USD',	'BUSD'),
(37,	'UNUS SED LEO',	'LEO'),
(38,	'Terra',	'LUNA'),
(39,	'Elrond',	'EGLD'),
(40,	'FTX Token',	'FTT'),
(41,	'IOTA',	'MIOTA'),
(42,	'Avalanche',	'AVAX'),
(43,	'Celsius',	'CEL'),
(44,	'Dash',	'DASH'),
(45,	'Filecoin',	'FIL'),
(46,	'The Graph',	'GRT'),
(47,	'Zcash',	'ZEC'),
(48,	'Kusama',	'KSM'),
(49,	'Revain',	'REV'),
(50,	'Decred',	'DCR'),
(51,	'yearn.finance',	'YFI'),
(52,	'Algorand',	'ALGO'),
(53,	'Ethereum Classic',	'ETC'),
(54,	'Ren',	'REN'),
(55,	'Zilliqa',	'ZIL'),
(56,	'SwissBorg',	'CHSB'),
(57,	'Waves',	'WAVES'),
(58,	'0x',	'ZRX'),
(59,	'Nexo',	'NEXO'),
(60,	'NEAR Protocol',	'NEAR'),
(61,	'Curve DAO Token',	'CRV'),
(62,	'Loopring',	'LRC'),
(63,	'OMG Network',	'OMG'),
(64,	'Hedera Hashgraph',	'HBAR'),
(65,	'THORChain',	'RUNE'),
(66,	'renBTC',	'RENBTC'),
(67,	'1inch',	'1INCH'),
(68,	'Voyager Token',	'VGX'),
(69,	'Celo',	'CELO'),
(70,	'Ontology',	'ONT'),
(71,	'HedgeTrade',	'HEDG'),
(72,	'Basic Attention Token',	'BAT'),
(73,	'HUSD',	'HUSD'),
(74,	'Nano',	'NANO'),
(75,	'ICON',	'ICX'),
(76,	'DigiByte',	'DGB'),
(77,	'Quant',	'QNT'),
(78,	'BitTorrent',	'BTT'),
(79,	'Alpha Finance Lab',	'ALPHA'),
(80,	'Siacoin',	'SC'),
(81,	'Horizen',	'ZEN'),
(82,	'Reserve Rights',	'RSR'),
(83,	'TrueUSD',	'TUSD'),
(84,	'OKB',	'OKB'),
(85,	'Ampleforth',	'AMPL'),
(86,	'Fantom',	'FTM'),
(87,	'Qtum',	'QTUM'),
(88,	'Stacks',	'STX'),
(89,	'Kyber Network',	'KNC'),
(90,	'Enjin Coin',	'ENJ'),
(91,	'Ocean Protocol',	'OCEAN'),
(92,	'PancakeSwap',	'CAKE'),
(93,	'FunFair',	'FUN'),
(94,	'Verge',	'XVG'),
(95,	'Bancor',	'BNT'),
(96,	'IOST',	'IOST'),
(97,	'Decentraland',	'MANA'),
(98,	'TerraUSD',	'UST'),
(99,	'Bitcoin BEP2',	'BTCB'),
(100,	'Paxos Standard',	'PAX');

INSERT INTO "users" ("username", "password", "email") VALUES
('freeman.mohr',	'0l6lyyy8x',	'Romana.Pagac@gmail.com'),
('tobias.shields',	's26i95xz49e4ko',	'Lasandra.Ward@gmail.com'),
('houston.luettgen',	'lkf9ttsxcy',	'Virgilio.Miller@gmail.com'),
('gladis.trantow',	'fcq9s1rmg',	'Colby.Wiza@gmail.com'),
('anita.flatley',	'3y23lwum',	'Clinton.Douglas@gmail.com'),
('belva.nitzsche',	'xrqgmnocosaqm',	'Reynalda.Simonis@gmail.com'),
('modesto.tromp',	'bw8n3cf9',	'Cristobal.Kub@gmail.com'),
('delcie.beatty',	'gb3uyxo4pmk9',	'Renato.Gerlach@gmail.com'),
('harris.heller',	'szzrv2defr',	'Hayden.Goyette@gmail.com'),
('victor.block',	'1ywk3oqq',	'Logan.Toy@gmail.com'),
('eric.dietrich',	'mkrwc894nn9ir2',	'Hershel.Adams@gmail.com');

INSERT INTO "transaction" ("id", "user_id", "crypto_currency_id", "amount", "operation_type", "transaction_date") VALUES
(1,	1,	98,	145,	'deposit',	'2022-07-01T16:27:31.467407298Z'),
(2,	1,	98,	28,	'deposit',	'2022-06-29T16:27:31.714162506Z'),
(3,	1,	98,	290,	'deposit',	'2022-07-01T16:27:31.719092037Z'),
(4,	1,	92,	470,	'deposit',	'2022-06-27T16:27:31.723346393Z'),
(5,	1,	92,	144,	'deposit',	'2022-06-28T16:27:31.727602685Z'),
(6,	1,	92,	32,	'deposit',	'2022-06-30T16:27:31.732991007Z'),
(7,	2,	41,	447,	'deposit',	'2022-07-01T16:27:31.736892828Z'),
(8,	2,	41,	36,	'deposit',	'2022-07-01T16:27:31.740340658Z'),
(9,	2,	41,	107,	'deposit',	'2022-06-28T16:27:31.745048444Z'),
(10,	3,	75,	288,	'deposit',	'2022-06-29T16:27:31.749356151Z'),
(11,	3,	75,	27,	'deposit',	'2022-06-30T16:27:31.754012118Z'),
(12,	3,	75,	337,	'deposit',	'2022-06-30T16:27:31.757926037Z'),
(13,	3,	45,	184,	'deposit',	'2022-07-01T16:27:31.761815899Z'),
(14,	3,	45,	556,	'deposit',	'2022-07-01T16:27:31.765659299Z'),
(15,	3,	45,	194,	'deposit',	'2022-06-27T16:27:31.769908452Z'),
(16,	3,	89,	611,	'deposit',	'2022-06-30T16:27:31.773517849Z'),
(17,	3,	89,	80,	'deposit',	'2022-06-30T16:27:31.776916473Z'),
(18,	3,	89,	31,	'deposit',	'2022-07-01T16:27:31.781614166Z'),
(19,	4,	97,	216,	'deposit',	'2022-06-30T16:27:31.785522515Z'),
(20,	4,	97,	414,	'deposit',	'2022-07-01T16:27:31.788931297Z'),
(21,	4,	97,	337,	'deposit',	'2022-06-27T16:27:31.792452292Z'),
(22,	4,	50,	199,	'deposit',	'2022-07-01T16:27:31.798496094Z'),
(23,	4,	50,	253,	'deposit',	'2022-06-30T16:27:31.802201076Z'),
(24,	4,	50,	51,	'deposit',	'2022-06-28T16:27:31.805952931Z'),
(25,	4,	11,	94,	'deposit',	'2022-06-30T16:27:31.809627538Z'),
(26,	4,	11,	15,	'deposit',	'2022-06-30T16:27:31.813278207Z'),
(27,	4,	11,	36,	'deposit',	'2022-06-30T16:27:31.818003236Z'),
(28,	5,	81,	523,	'deposit',	'2022-06-30T16:27:31.821657049Z'),
(29,	5,	81,	145,	'deposit',	'2022-06-29T16:27:31.824784446Z'),
(30,	5,	81,	99,	'deposit',	'2022-06-30T16:27:31.827762114Z'),
(31,	5,	11,	408,	'deposit',	'2022-06-29T16:27:31.831155072Z'),
(32,	5,	11,	1,	'deposit',	'2022-06-27T16:27:31.835033440Z'),
(33,	5,	11,	0,	'deposit',	'2022-06-27T16:27:31.839201475Z'),
(34,	6,	28,	79,	'deposit',	'2022-06-27T16:27:31.842272039Z'),
(35,	6,	28,	311,	'deposit',	'2022-06-30T16:27:31.845190867Z'),
(36,	6,	28,	42,	'deposit',	'2022-07-01T16:27:31.849139819Z'),
(37,	7,	91,	553,	'deposit',	'2022-06-27T16:27:31.852026666Z'),
(38,	7,	91,	30,	'deposit',	'2022-06-29T16:27:31.854682759Z'),
(39,	7,	91,	93,	'deposit',	'2022-07-01T16:27:31.858306094Z'),
(40,	7,	91,	112, 'withdraw',	'2022-07-01T16:27:31.858306094Z'),
(41,	6,	28,	12,	'withdraw',	'2022-06-27T16:27:31.842272039Z'),
(42,	6,	28,	7,	'withdraw',	'2022-06-30T16:27:31.845190867Z'),
(43,	6,	28,	9,	'withdraw',	'2022-07-01T16:27:31.849139819Z'),
(44,	4,	11,	43,	'withdraw',	'2022-06-30T16:27:31.818003236Z'),
(45,	4,	97,	110,	'withdraw',	'2022-07-01T16:27:31.788931297Z'),
(46,	4,	97,	34,	'withdraw',	'2022-06-27T16:27:31.792452292Z'),
(47,	3,	75,	11,	'withdraw',	'2022-06-30T16:27:31.754012118Z'),
(48,	3,	75,	223,	'withdraw',	'2022-06-30T16:27:31.757926037Z'),
(49,	3,	45,	45,	'withdraw',	'2022-06-27T16:27:31.769908452Z'),
(50,	2,	41,	12,	'withdraw',	'2022-06-28T16:27:31.745048444Z'),
(51,	1,	98,	38,	'withdraw',	'2022-06-29T16:27:31.714162506Z'),
(52,	1,	98,	150, 'withdraw',	'2022-07-01T16:27:31.719092037Z'),
(53,	3,	89,	12,	'withdraw',	'2022-06-30T16:27:31.776916473Z'),
(54,	3,	89,	66,	'withdraw',	'2022-07-01T16:27:31.781614166Z');